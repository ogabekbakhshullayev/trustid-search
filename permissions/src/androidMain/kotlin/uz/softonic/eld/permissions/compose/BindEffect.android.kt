package uz.softonic.eld.permissions.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import uz.softonic.eld.permissions.PermissionsController

@Composable
actual fun BindPermissionsEffect(controller: PermissionsController) {
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val context: Context = LocalContext.current

    LaunchedEffect(controller, lifecycleOwner, context) {
        val fragmentManager: FragmentManager = (context as FragmentActivity).supportFragmentManager
        controller.bind(lifecycleOwner.lifecycle, fragmentManager)
    }
}
