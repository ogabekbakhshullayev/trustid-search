package uz.aigroup.trustiddemo.screen.auth

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.aigroup.trustiddemo.data.remote.request.AccessTokenRequest
import uz.aigroup.trustiddemo.data.remote.util.onFailure
import uz.aigroup.trustiddemo.data.remote.util.onSuccess
import uz.aigroup.trustiddemo.data.repository.SearchRepository
import uz.aigroup.trustiddemo.data.store.AppSettings
import uz.aigroup.trustiddemo.di.reloadKoinModules

class
AuthScreenModel : ScreenModel, KoinComponent {

    private val repository by inject<SearchRepository>()
    private val appSettings by inject<AppSettings>()

    private val stateData = MutableStateFlow(AuthState())
    val state = stateData.asStateFlow()

    init {
        stateData.update {
            it.copy(
                username = "kginfocom",
                password = "DYxtOBzNq8Iwiy!cH3jnMu"
            )
        }
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.ChangeUsername -> usernameChanged(event.username)
            is AuthEvent.ChangePassword -> passwordChanged(event.password)
            AuthEvent.Login -> login()
            AuthEvent.Idle -> setState()
        }
    }

    private fun usernameChanged(text: String) {
        stateData.update { it.copy(username = text) }
    }

    private fun passwordChanged(text: String) {
        stateData.update { it.copy(password = text) }
    }

    private fun login() {
        setState(loading = true)

        screenModelScope.launch {
            repository.auth(
                AccessTokenRequest(
                    login = state.value.username,
                    password = state.value.password
                )
            ).collectLatest {
                it onSuccess {
                    appSettings.setAccessToken(data?.accessToken)
                    reloadKoinModules()

                    setState(succeeded = !data?.accessToken.isNullOrEmpty())
                } onFailure {
                    setState(errorMessage = message)
                }
            }
        }
    }

    private fun setState(
        loading: Boolean = false,
        errorMessage: String? = null,
        succeeded: Boolean = false,
    ) {
        stateData.update {
            it.copy(
                loading = loading,
                errorMessage = errorMessage,
                succeeded = succeeded,
            )
        }
    }
}