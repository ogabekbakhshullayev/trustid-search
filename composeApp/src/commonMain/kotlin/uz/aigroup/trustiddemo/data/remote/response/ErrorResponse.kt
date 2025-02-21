package uz.aigroup.trustiddemo.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ErrorResponse(
    @SerialName("detail")
    val detail: JsonElement? = null
)
