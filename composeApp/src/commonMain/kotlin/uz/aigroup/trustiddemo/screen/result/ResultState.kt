package uz.aigroup.trustiddemo.screen.result

data class ResultState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val result: String? = null
)
