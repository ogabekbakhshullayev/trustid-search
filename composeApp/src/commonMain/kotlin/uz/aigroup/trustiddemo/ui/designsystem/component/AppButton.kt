package uz.aigroup.trustiddemo.ui.designsystem.component

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun AppFilledButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .widthIn(min = AppButtonDefaults.NormalButtonWidth)
            .heightIn(min = AppButtonDefaults.NormalButtonHeight),
        enabled = enabled,
        colors = AppButtonDefaults.filledButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = shape,
        content = {
            ProvideTextStyle(value = textStyle) {
                Text(text = text)
            }
        }
    )
}

@Composable
fun AppTextButton(
    text: String,
    enabled: Boolean = true,
    hasStroke: Boolean = false,
    containerColor: Color = Color.Transparent,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        colors = AppButtonDefaults.textButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = shape,
        content = {
            ProvideTextStyle(value = textStyle) {
                Text(
                    text = text,
                    textDecoration = if (hasStroke) {
                        TextDecoration.Underline
                    } else {
                        TextDecoration.None
                    }
                )
            }
        }
    )
}

private object AppButtonDefaults {

    val NormalButtonWidth = 160.dp
    val NormalButtonHeight = 42.dp

    private const val DisabledButtonContainerAlpha = 0.27f
    private const val DisabledButtonContentAlpha = 0.38f

    @Composable
    fun filledButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.primary,
        contentColor: Color = MaterialTheme.colorScheme.onPrimary
    ) = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = MaterialTheme.colorScheme.onBackground.copy(
            alpha = DisabledButtonContainerAlpha
        ),
        disabledContentColor = Color.White
    )

    @Composable
    fun textButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = MaterialTheme.colorScheme.onBackground
    ) = ButtonDefaults.textButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = containerColor,
        disabledContentColor = contentColor.copy(
            alpha = DisabledButtonContentAlpha
        )
    )
}