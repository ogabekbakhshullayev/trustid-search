package uz.aigroup.trustiddemo.data.remote.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("atto_user_id")
    val userId: String? = null,
)

@Serializable
data class TaskResponse(
    @SerialName("task_id")
    val taskId: String? = null,
)

@Serializable
data class ResultResponse(
    @SerialName("data")
    val resultData: String?= null,
    @SerialName("state")
    val state: String? = null,
)