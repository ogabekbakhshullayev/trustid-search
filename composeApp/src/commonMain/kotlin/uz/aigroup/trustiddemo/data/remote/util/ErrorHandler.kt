package uz.aigroup.trustiddemo.data.remote.util

import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.utils.io.errors.IOException

object ErrorHandler {

    fun resolveException(throwable: Throwable? = null): UiState.Failure {
        val message = when (throwable) {
            is SocketTimeoutException,
            is ConnectTimeoutException,
            is IOException,
            -> ErrorMessages.NetworkError

            else -> throwable?.message ?: ErrorMessages.InternalError
        }

        return UiState.failure(message)
    }
}
