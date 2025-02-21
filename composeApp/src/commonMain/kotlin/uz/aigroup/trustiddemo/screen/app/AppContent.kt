package uz.aigroup.trustiddemo.screen.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
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
            }
        }
    }
}