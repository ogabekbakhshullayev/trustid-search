package uz.aigroup.trustiddemo.platform

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.Auth.Plugin.AuthCircuitBreaker
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.auth.parseAuthorizationHeader
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.AttributeKey
import io.ktor.util.InternalAPI
import io.ktor.util.KtorDsl
import kotlinx.serialization.json.Json
import uz.aigroup.trustiddemo.data.store.AppSettings

fun HttpClientConfig<*>.configureKtor(
    appSettings: AppSettings,
) {
    install(HttpTimeout) {
        requestTimeoutMillis = 60 * 1000L
        connectTimeoutMillis = 60 * 1000L
        socketTimeoutMillis = 60 * 1000L
    }
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }

    followRedirects = false

    refreshToken {
        bearer {
            loadTokens {
                BearerTokens(
                    accessToken = appSettings.getAccessToken(),
                    refreshToken = appSettings.getRefreshToken()
                )
            }
            refreshTokens {
                BearerTokens(
                    accessToken = appSettings.getAccessToken(),
                    refreshToken = appSettings.getRefreshToken()
                )
            }
        }
    }
}

@KtorDsl
class BearerAuth {

    companion object Plugin : HttpClientPlugin<Auth, Auth> {

        override val key: AttributeKey<Auth> = AttributeKey("DigestAuth")

        override fun prepare(block: Auth.() -> Unit): Auth {
            return Auth.prepare(block)
        }

        @OptIn(InternalAPI::class)
        override fun install(plugin: Auth, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.State) {
                plugin.providers.filter { it.sendWithoutRequest(context) }.forEach {
                    it.addRequestHeaders(context)
                }
            }

            scope.plugin(HttpSend).intercept { context ->
                val origin = execute(context)

                if (
                    origin.response.status == HttpStatusCode.Unauthorized ||
                    origin.response.status == HttpStatusCode.Forbidden
                ) {
                    if (origin.request.attributes.contains(AuthCircuitBreaker)) return@intercept origin

                    var call = origin

                    val candidateProviders = HashSet(plugin.providers)

                    while (call.response.status == HttpStatusCode.Unauthorized
                        || call.response.status == HttpStatusCode.Forbidden
                    ) {
                        val headerValue = call.response.headers[HttpHeaders.WWWAuthenticate]

                        val authHeader = headerValue?.let { parseAuthorizationHeader(headerValue) }
                        val provider = when {
                            authHeader == null && candidateProviders.size == 1 -> candidateProviders.first()
                            authHeader == null -> return@intercept call
                            else -> candidateProviders.find { it.isApplicable(authHeader) }
                                ?: return@intercept call
                        }
                        if (!provider.refreshToken(call.response)) return@intercept call

                        candidateProviders.remove(provider)

                        val request = HttpRequestBuilder()
                        request.takeFromWithExecutionContext(context)
                        provider.addRequestHeaders(request, authHeader)
                        request.attributes.put(AuthCircuitBreaker, Unit)

                        call = execute(request)
                    }
                    return@intercept call
                } else {
                    return@intercept origin
                }
            }
        }
    }
}

fun HttpClientConfig<*>.refreshToken(block: Auth.() -> Unit) {
    install(BearerAuth, block)
}