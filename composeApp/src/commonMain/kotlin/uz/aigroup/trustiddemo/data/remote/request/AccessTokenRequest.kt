package uz.aigroup.trustiddemo.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenRequest(
    @SerialName(value = "username") val login: String? = null,
    @SerialName(value = "password") val password: String? = null,
)
