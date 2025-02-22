package uz.aigroup.trustiddemo.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    @SerialName("state") val state: State? = null,
) {
    @Serializable
    data class State(
        @SerialName("code") val code: Int? = null,
        @SerialName("message") val message: String? = null,
    )
}
