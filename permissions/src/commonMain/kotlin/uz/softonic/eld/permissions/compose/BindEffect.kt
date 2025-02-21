package uz.softonic.eld.permissions.compose

import androidx.compose.runtime.Composable
import uz.softonic.eld.permissions.PermissionsController

@Composable
expect fun BindPermissionsEffect(controller: PermissionsController)
