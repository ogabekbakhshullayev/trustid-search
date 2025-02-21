package uz.softonic.eld.permissions.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import uz.softonic.eld.permissions.PermissionsController
import uz.softonic.eld.permissions.compose.PermissionsControllerFactory

@Composable
actual fun rememberPermissionsControllerFactory(): PermissionsControllerFactory {
    val context: Context = LocalContext.current

    return remember(context) {
        PermissionsControllerFactory {
            PermissionsController(applicationContext = context.applicationContext)
        }
    }
}
