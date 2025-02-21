package uz.aigroup.trustiddemo.data.remote.util

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive
import uz.aigroup.trustiddemo.data.remote.response.ErrorResponse

suspend inline fun <reified T> getResult(
    response: HttpResponse,
): UiState.Success<T> {
    return UiState.success(response.body<T>())
}

suspend infix fun HttpResponse.onSuccess(onSuccess: suspend HttpResponse.() -> Unit): HttpResponse {
    return when (status.value) {
        in 200..299 -> {
            onSuccess(this)
            this
        }

        else -> this
    }
}

suspend infix fun HttpResponse.onFailure(onFailure: suspend UiState.Failure.() -> Unit): HttpResponse {
    return when (status.value) {
        in 400..499 -> {
            onFailure(UiState.failure(bodyAsText().decodeFromString()))
            this
        }

        in 300..399,
        in 500..599,
        -> {
            onFailure(UiState.failure(bodyAsText()))
            this
        }

        else -> this
    }
}

private fun String.decodeFromString(): String {
    val message = try {
        val json = Json { ignoreUnknownKeys = true }
        val response = json.decodeFromString<ErrorResponse>(this)

        when (response.detail) {
            is JsonObject -> response.detail.toString()
            is JsonPrimitive -> response.detail.jsonPrimitive.content
            else -> ErrorMessages.InvalidRequest
        }
    } catch (t: Throwable) {
        t.message
    }

    return message.orEmpty().ifEmpty { ErrorMessages.InvalidRequest }
}