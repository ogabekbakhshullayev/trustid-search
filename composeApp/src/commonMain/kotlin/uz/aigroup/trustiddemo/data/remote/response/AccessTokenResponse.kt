package uz.aigroup.trustiddemo.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenResponse(
    @SerialName(value = "access_token")
    val accessToken: String? = null,
)