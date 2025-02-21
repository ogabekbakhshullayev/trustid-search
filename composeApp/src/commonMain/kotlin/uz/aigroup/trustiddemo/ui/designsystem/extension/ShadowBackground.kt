package uz.aigroup.trustiddemo.ui.designsystem.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uz.aigroup.trustiddemo.ui.designsystem.theme.ShadowColor

fun Modifier.shadowBackground(
    elevation: Dp = 6.dp,
    shape: Shape = RectangleShape,
) = composed {
    Modifier.shadow(
        elevation = elevation,
        shape = shape,
        ambientColor = ShadowColor,
        spotColor = ShadowColor,
    )
}