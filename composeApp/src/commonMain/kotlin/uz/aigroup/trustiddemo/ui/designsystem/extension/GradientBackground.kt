package uz.aigroup.trustiddemo.ui.designsystem.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.pow

fun Modifier.gradientBackground(
    colors: List<Color> = listOf(
        Color(0xFF284152),
        Color(0xFF132735),
    ),
    angle: Float = 315f,
) = this.then(
    Modifier.drawBehind {
        val angleRad = angle / 180f * 3.14
        val x = kotlin.math.cos(angleRad).toFloat() // Fractional x
        val y = kotlin.math.sin(angleRad).toFloat() // Fractional y

        val radius = kotlin.math.sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        val exactOffset = Offset(
            x = offset.x.coerceAtLeast(0f).coerceAtMost(size.width),
            y = size.height - offset.y.coerceAtLeast(0f).coerceAtMost(size.height)
        )

        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)