package uz.aigroup.trustiddemo.ui.designsystem.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uz.aigroup.trustiddemo.ui.designsystem.theme.LocalBackgroundTheme

@Composable
fun AppBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val color = LocalBackgroundTheme.current.color
    val tonalElevation = LocalBackgroundTheme.current.tonalElevation

    Surface(
        color = if (color == Color.Unspecified) Color.Transparent else color,
        tonalElevation = if (tonalElevation == Dp.Unspecified) 0.dp else tonalElevation,
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.ime)
    ) {
        CompositionLocalProvider(LocalAbsoluteTonalElevation provides 0.dp) {
            content()
        }
    }
}