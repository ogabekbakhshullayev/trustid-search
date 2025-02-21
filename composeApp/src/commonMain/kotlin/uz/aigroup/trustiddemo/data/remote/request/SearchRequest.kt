package uz.aigroup.piima.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchRequest(
    @SerialName(value = "camera_photo")
    val cameraPhoto: String? = null,
)
