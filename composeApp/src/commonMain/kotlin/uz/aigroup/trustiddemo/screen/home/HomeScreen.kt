package uz.aigroup.trustiddemo.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import uz.aigroup.trustiddemo.platform.rememberCameraManager
import uz.aigroup.trustiddemo.ui.designsystem.component.AppBackground
import uz.aigroup.trustiddemo.ui.designsystem.component.AppFilledButton
import uz.aigroup.trustiddemo.ui.designsystem.component.AppTextField
import uz.aigroup.trustiddemo.ui.designsystem.localization.AppStrings
import uz.softonic.eld.permissions.DeniedAlwaysException
import uz.softonic.eld.permissions.DeniedException
import uz.softonic.eld.permissions.Permission
import uz.softonic.eld.permissions.compose.BindPermissionsEffect
import uz.softonic.eld.permissions.compose.rememberPermissionsControllerFactory

object HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = rememberScreenModel { HomeScreenModel() }
        val state by screenModel.state.collectAsState()

        HomeScreenContent(
            state = state,
            event = screenModel::onEvent
        )
    }

    @Composable
    private fun HomeScreenContent(
        state: HomeState,
        event: (HomeEvent) -> Unit,
    ) {
        val coroutineScope = rememberCoroutineScope()
        val factory = rememberPermissionsControllerFactory()
        val controller = remember(factory) {
            factory.createPermissionsController()
        }

        BindPermissionsEffect(controller)

        val cameraManager = rememberCameraManager { }

        AppBackground {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 24.dp,
                    alignment = Alignment.CenterVertically
                ),
            ) {
                AppTextField(
                    value = state.groupId,
                    onValueChange = {
                        event(HomeEvent.ChangeGroupId(it))
                    },
                    hint = AppStrings.groupId,
                    placeholder = AppStrings.enterGroupId,
                    imeAction = ImeAction.Done,
                )

                AppFilledButton(
                    text = AppStrings.next,
                    enabled = state.enabled
                ) {
                    coroutineScope.launch {
                        controller.providePermission(Permission.CAMERA)
                            .onSuccess {
                                cameraManager.launch()
                            }
                            .onFailure {
                                when (it) {
                                    is DeniedException,
                                    is DeniedAlwaysException,
                                    -> {
                                        controller.openAppSettings()
                                    }
                                }
                            }
                    }
                }
            }
        }
    }
}