package uz.aigroup.trustiddemo.ui.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uz.aigroup.trustiddemo.ui.designsystem.icon.AppIcons

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    trailingAction: (@Composable BoxScope.() -> Unit)? = null,
    showLogo: Boolean = false,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    onNavigateUp: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .statusBarsPadding()
            .fillMaxWidth()
    ) {
        onNavigateUp?.let {
            IconButton(
                onClick = it,
                modifier = Modifier
                    .padding(6.dp)
                    .align(Alignment.CenterStart)
            ) {
                AppIcon(
                    imageVector = AppIcons.arrowBack,
                    color = contentColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        if (showLogo) {
            Image(
                imageVector = AppIcons.whiteLogo,
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.Center)
                    .width(150.dp)
                    .height(40.dp)
            )
        }

        trailingAction?.invoke(this)
    }
}