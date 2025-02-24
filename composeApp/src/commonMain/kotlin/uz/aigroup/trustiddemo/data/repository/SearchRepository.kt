package uz.aigroup.trustiddemo.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import uz.aigroup.trustiddemo.data.remote.request.AccessTokenRequest
import uz.aigroup.trustiddemo.data.remote.request.SearchRequest
import uz.aigroup.trustiddemo.data.remote.response.AccessTokenResponse
import uz.aigroup.trustiddemo.data.remote.response.ResultResponse
import uz.aigroup.trustiddemo.data.remote.response.TaskResponse
import uz.aigroup.trustiddemo.data.remote.util.ErrorHandler
import uz.aigroup.trustiddemo.data.remote.util.HttpRoutes
import uz.aigroup.trustiddemo.data.remote.util.UiState
import uz.aigroup.trustiddemo.data.remote.util.getResult
import uz.aigroup.trustiddemo.data.remote.util.onFailure
import uz.aigroup.trustiddemo.data.remote.util.onSuccess
import uz.aigroup.trustiddemo.data.store.AppSettings
import uz.aigroup.trustiddemo.platform.Event
import uz.aigroup.trustiddemo.platform.EventChannel

class SearchRepository(
    private val client: HttpClient,
    private val appSettings: AppSettings,
) : KoinComponent {

    suspend fun auth(request: AccessTokenRequest): Flow<UiState<AccessTokenResponse>> {
        return flow {
            client.post {
                url(HttpRoutes.accessToken)
                contentType(ContentType.Application.Json)
                setBody(request)
            } onSuccess {
                emit(getResult<AccessTokenResponse>(this))
            } onFailure {
                emit(this)
            }
        }.catch { exception ->
            emit(ErrorHandler.resolveException(exception))
        }
    }

    suspend fun search(request: SearchRequest): Flow<UiState<TaskResponse>> {
        return flow {
            val httpResponse = client.post {
                url(HttpRoutes.search)
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            if (httpResponse.status.value == 401 || httpResponse.status.value == 504) {
                appSettings.setAccessToken("")

                EventChannel.sendEvent(Event.Unauthorized)
            }

            httpResponse onSuccess {
                emit(getResult<TaskResponse>(this))
            } onFailure {
                emit(this)
            }
        }.catch { cause ->
            emit(ErrorHandler.resolveException(cause))
        }
    }

    suspend fun searchResult(taskId: String?): Flow<UiState<ResultResponse>> {
        return flow {
            val httpResponse = client.get {
                url(HttpRoutes.task + taskId)
                contentType(ContentType.Application.Json)
                parameter("timeout", 5)
            }

            if (httpResponse.status.value == 401 || httpResponse.status.value == 504) {
                appSettings.setAccessToken("")

                EventChannel.sendEvent(Event.Unauthorized)
            }

            httpResponse onSuccess {
                emit(getResult<ResultResponse>(this))
            } onFailure {
                emit(this)
            }
        }.catch { cause ->
            emit(ErrorHandler.resolveException(cause))
        }
    }
}
