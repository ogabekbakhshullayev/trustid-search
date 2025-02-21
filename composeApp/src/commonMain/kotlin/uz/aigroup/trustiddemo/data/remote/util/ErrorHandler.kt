package uz.aigroup.trustiddemo.data.remote.util

import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.utils.io.errors.IOException

object ErrorHandler {

    fun resolveException(cause: Throwable? = null): UiState.Failure {
        val message = when (cause) {
            is SocketTimeoutException,
            is ConnectTimeoutException,
            is IOException,
            -> ErrorMessages.NetworkError

            else -> cause?.message ?: ErrorMessages.InternalError
        }

        return UiState.failure(message)
    }
}
