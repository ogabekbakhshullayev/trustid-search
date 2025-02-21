package uz.aigroup.trustiddemo.ui.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uz.aigroup.trustiddemo.ui.designsystem.theme.DividerColor

@Composable
fun DividerContent(
    modifier: Modifier = Modifier,
    color: Color = DividerColor,
) {
    HorizontalDivider(
        modifier = modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = color,
    )
}