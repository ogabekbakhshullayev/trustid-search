package uz.aigroup.trustiddemo.ui.designsystem.component

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

enum class LoadingSize {
    Large,
    Medium,
    Small
}

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier,
    size: LoadingSize = LoadingSize.Large,
) {
    CircularProgressIndicator(
        modifier = modifier.requiredSize(
            when(size) {
                LoadingSize.Large -> 60.dp
                LoadingSize.Medium -> 45.dp
                LoadingSize.Small -> 30.dp
            }
        ),
        color = MaterialTheme.colorScheme.primary,
        strokeWidth = when(size) {
            LoadingSize.Large -> 6.dp
            LoadingSize.Medium -> 4.dp
            LoadingSize.Small -> 2.dp
        },
        trackColor = MaterialTheme.colorScheme.primary.copy(alpha = .1f),
        strokeCap = StrokeCap.Round,
    )
}