package uz.aigroup.trustiddemo.screen.auth

data class AuthState(
    val username: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val succeeded: Boolean = false
)
