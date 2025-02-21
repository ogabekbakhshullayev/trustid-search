package uz.aigroup.trustiddemo.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchRequest(
    @SerialName(value = "camera_photo") val cameraPhoto: String? = null,
    @SerialName(value = "group_id") val groupId: String? = null,
)