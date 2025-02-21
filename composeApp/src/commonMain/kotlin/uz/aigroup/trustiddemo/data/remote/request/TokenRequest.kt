package uz.aigroup.trustiddemo.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenRequest(
    @SerialName(value = "login")
    val login: String? = null,
    @SerialName(value = "password")
    val password: String? = null
)
