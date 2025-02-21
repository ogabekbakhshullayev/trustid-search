package uz.aigroup.trustiddemo.screen.auth

sealed class AuthEvent {
    data class ChangeUsername(val username: String) : AuthEvent()
    data class ChangePassword(val password: String) : AuthEvent()
    data object Login : AuthEvent()
    data object Idle : AuthEvent()
}
