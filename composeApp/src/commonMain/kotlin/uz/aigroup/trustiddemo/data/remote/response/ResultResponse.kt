package uz.aigroup.trustiddemo.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    @SerialName("state") val state: State? = null,
    @SerialName("data") val data: Data? = null,
) {
    @Serializable
    data class State(
        @SerialName("code") val code: Int? = null,
        @SerialName("message") val message: String? = null,
    )

    @Serializable
    data class Data(
        @SerialName("results") val results: List<Item>? = null,
    )

    @Serializable
    data class Item(
        @SerialName("personId") val personId: String? = null,
        @SerialName("recognitionValue") val recognitionValue: Int? = null,
    )
}
