package uz.softonic.eld.permissions.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import uz.softonic.eld.permissions.compose.PermissionsControllerFactory
import uz.softonic.eld.permissions.ios.PermissionsController

@Composable
actual fun rememberPermissionsControllerFactory(): PermissionsControllerFactory {
    return remember {
        PermissionsControllerFactory { PermissionsController() }
    }
}
