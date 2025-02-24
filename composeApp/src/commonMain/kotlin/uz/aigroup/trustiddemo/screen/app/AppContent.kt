package uz.aigroup.trustiddemo.screen.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import kotlinx.coroutines.flow.collectLatest
import uz.aigroup.trustiddemo.platform.Event
import uz.aigroup.trustiddemo.platform.EventChannel
import uz.aigroup.trustiddemo.screen.auth.AuthScreen
import uz.aigroup.trustiddemo.screen.home.HomeScreen
import uz.aigroup.trustiddemo.ui.designsystem.theme.AppTheme

@Composable
fun AppContent(onboarding: Boolean) {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Navigator(if (onboarding) AuthScreen else HomeScreen) {
                SlideTransition(it) { screen -> screen.Content() }

                HandleEventChannel(navigator = it)
            }
        }
    }
}

@Composable
private fun HandleEventChannel(navigator: Navigator) {
    LaunchedEffect(Unit) {
        EventChannel.receiveEvent().collectLatest { event ->
            when (event) {
                is Event.Unauthorized -> {
                    navigator.replaceAll(AuthScreen)
                }
            }
        }
    }
}