package uz.aigroup.trustiddemo.ui.designsystem.component

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppSwitch(
    checkedTrackColor: Color,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedTrackColor = checkedTrackColor,
            uncheckedThumbColor = Color(0xFFC9C9C9),
            uncheckedTrackColor = Color(0xFFF5F6F6),
            uncheckedBorderColor = Color(0xFFC9C9C9),
        ),
    )
}