package uz.aigroup.trustiddemo.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.aigroup.trustiddemo.platform.toast
import uz.aigroup.trustiddemo.screen.home.HomeScreen
//import uz.aigroup.trustiddemo.screen.voice.VoiceScreen
import uz.aigroup.trustiddemo.ui.designsystem.component.AppBackground
import uz.aigroup.trustiddemo.ui.designsystem.component.AppFilledButton
import uz.aigroup.trustiddemo.ui.designsystem.component.AppTextField
import uz.aigroup.trustiddemo.ui.designsystem.icon.AppIcons
import uz.aigroup.trustiddemo.ui.designsystem.localization.AppStrings

object AuthScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel {
            AuthScreenModel(
                username = "spazzlite",
                password = "P@\$\$w0rd"
            )
        }
        val state by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(state.errorMessage) {
            if (!state.errorMessage.isNullOrEmpty()) {
                toast(state.errorMessage)
                screenModel.onEvent(AuthEvent.Idle)
            }
        }

        LaunchedEffect(state.succeeded) {
            if (state.succeeded) {
                navigator.replaceAll(HomeScreen)
            }
        }

        AppBackground {
            AuthScreenContent(
                state = state,
                onEvent = screenModel::onEvent
            )
        }
    }

    @Composable
    private fun AuthScreenContent(
        state: AuthState,
        onEvent: (AuthEvent) -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Spacer(Modifier)

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(20.dp),
            ) {
                item {
                    Text(
                        text = AppStrings.registration,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
                item {
                    AppTextField(
                        value = state.username,
                        onValueChange = {
                            onEvent(AuthEvent.ChangeUsername(it))
                        },
                        hint = AppStrings.username,
                        placeholder = AppStrings.enterUsername,
                        imeAction = ImeAction.Next,
                    )
                }
                item {
                    AppTextField(
                        value = state.password,
                        onValueChange = {
                            onEvent(AuthEvent.ChangePassword(it))
                        },
                        hint = AppStrings.password,
                        placeholder = AppStrings.enterPassword,
                        imeAction = ImeAction.Done,
                    )
                }
                item {
                    AppFilledButton(
                        text = AppStrings.next,
                        enabled = !state.loading,
                        onClick = { onEvent(AuthEvent.Login) }
                    )
                }
            }

            Image(
                imageVector = AppIcons.darkLogo,
                contentDescription = null,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
    }
}