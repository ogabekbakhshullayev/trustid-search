package uz.aigroup.trustiddemo.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    @SerialName("state") val state: String? = null,
)