package uz.aigroup.trustiddemo.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uz.aigroup.trustiddemo.ui.designsystem.extension.gradientBackground

@Composable
fun GradientHeader(
    title: String = "",
    description: String = "",
    trailingAction: (@Composable BoxScope.() -> Unit)? = null,
    showLogo: Boolean = false,
    contentColor: Color = Color.White,
    paddingBottom: Dp = 16.dp,
    onNavigateUp: (() -> Unit)? = null,
    content: (@Composable ColumnScope.() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .gradientBackground()
            .windowInsetsPadding(WindowInsets.statusBars),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (onNavigateUp != null) {
            AppTopBar(
                trailingAction = trailingAction,
                showLogo = showLogo,
                contentColor = contentColor,
                onNavigateUp = onNavigateUp,
            )
        } else if (showLogo) {
            AppTopBar(
                trailingAction = trailingAction,
                showLogo = true,
                contentColor = contentColor,
            )
        } else {
            Spacer(Modifier.height(24.dp))
        }

        if (title.isNotEmpty()) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = contentColor,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }

        if (description.isNotEmpty()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                color = contentColor,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .padding(bottom = paddingBottom)
            )
        }

        content?.invoke(this)
    }
}