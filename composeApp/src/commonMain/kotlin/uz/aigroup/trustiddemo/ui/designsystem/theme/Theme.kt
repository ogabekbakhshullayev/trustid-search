package uz.aigroup.trustiddemo.ui.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalBackgroundTheme provides LightBackgroundTheme,
    ) {
        SystemAppearance(false)

        MaterialTheme(
            colorScheme = LightColorScheme,
            typography = Typography,
            shapes = AppShapes,
            content = content
        )
    }
}

@Composable
expect fun SystemAppearance(darkMode: Boolean)