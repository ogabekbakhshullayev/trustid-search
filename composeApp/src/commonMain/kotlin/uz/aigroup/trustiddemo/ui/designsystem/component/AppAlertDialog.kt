package uz.aigroup.trustiddemo.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun AppAlertDialog(
    show: Boolean,
    title: String? = null,
    message: String? = null,
    cancelable: Boolean = true,
    usePlatformDefaultWidth: Boolean = true,
    positiveAction: String? = "OK",
    positiveContainerColor: Color = MaterialTheme.colorScheme.primary,
    positiveContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    negativeAction: String? = null,
    negativeContainerColor: Color = MaterialTheme.colorScheme.error,
    negativeContentColor: Color = MaterialTheme.colorScheme.onError,
    onPositiveAction: () -> Unit = {},
    onNegativeAction: () -> Unit = {},
    onDismissRequest: () -> Unit,
) {
    if (show) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnBackPress = cancelable,
                dismissOnClickOutside = cancelable,
                usePlatformDefaultWidth = usePlatformDefaultWidth,
            ),
        ) {
            DialogContent(
                title = title,
                message = message,
                positiveAction = positiveAction,
                positiveContainerColor = positiveContainerColor,
                positiveContentColor = positiveContentColor,
                negativeAction = negativeAction,
                negativeContainerColor = negativeContainerColor,
                negativeContentColor = negativeContentColor,
                onPositiveAction = onPositiveAction,
                onNegativeAction = onNegativeAction,
            )
        }
    }
}

@Composable
private fun DialogContent(
    title: String? = null,
    message: String? = null,
    positiveAction: String? = "OK",
    positiveContainerColor: Color = MaterialTheme.colorScheme.primary,
    positiveContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    negativeAction: String? = null,
    negativeContainerColor: Color = MaterialTheme.colorScheme.error,
    negativeContentColor: Color = MaterialTheme.colorScheme.onError,
    onPositiveAction: () -> Unit = {},
    onNegativeAction: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        title?.let {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
            )
        }

        message?.let {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        Spacer(Modifier)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            negativeAction?.let {
                AppFilledButton(
                    text = it,
                    modifier = Modifier.weight(1f),
                    containerColor = negativeContainerColor,
                    contentColor = negativeContentColor,
                    onClick = onNegativeAction,
                )
            }

            positiveAction?.let {
                AppFilledButton(
                    text = it,
                    modifier = Modifier.weight(1f),
                    containerColor = positiveContainerColor,
                    contentColor = positiveContentColor,
                    onClick = onPositiveAction,
                )
            }
        }

        Spacer(Modifier.height(12.dp))
    }
}
