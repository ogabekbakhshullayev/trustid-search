package uz.aigroup.trustiddemo.platform

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
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

    install(Auth) {
        bearer {
            loadTokens {
                BearerTokens(
                    accessToken = appSettings.getAccessToken(),
                    refreshToken = appSettings.getAccessToken()
                )
            }
            refreshTokens {
                appSettings.setAccessToken("")

                EventChannel.sendEvent(Event.Unauthorized)

                BearerTokens(
                    accessToken = "",
                    refreshToken = ""
                )
            }
        }
    }
}