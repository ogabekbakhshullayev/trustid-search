package uz.softonic.eld.permissions.compose

import androidx.compose.runtime.Composable
import uz.softonic.eld.permissions.PermissionsController

fun interface PermissionsControllerFactory {
    fun createPermissionsController(): PermissionsController
}

@Composable
expect fun rememberPermissionsControllerFactory(): PermissionsControllerFactory
