package uz.softonic.eld.permissions

import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import uz.softonic.eld.permissions.DeniedAlwaysException
import uz.softonic.eld.permissions.DeniedException
import uz.softonic.eld.permissions.Permission
import uz.softonic.eld.permissions.RequestCanceledException

internal class ResolverFragment : Fragment() {

    init {
        @Suppress("DEPRECATION")
        retainInstance = true
    }

    private var permissionCallback: PermissionCallback? = null

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionResults ->
            val permissionCallback = permissionCallback ?: return@registerForActivityResult
            this.permissionCallback = null

            val isCancelled = permissionResults.isEmpty()
            if (isCancelled) {
                permissionCallback.callback.invoke(
                    Result.failure(RequestCanceledException(permissionCallback.permission))
                )
                return@registerForActivityResult
            }

            val success = permissionResults.values.all { it }
            if (success) {
                permissionCallback.callback.invoke(Result.success(Unit))
            } else {
                if (shouldShowRequestPermissionRationale(permissionResults.keys.first())) {
                    permissionCallback.callback.invoke(
                        Result.failure(DeniedException(permissionCallback.permission))
                    )
                } else {
                    permissionCallback.callback.invoke(
                        Result.failure(DeniedAlwaysException(permissionCallback.permission))
                    )
                }
            }
        }

    fun requestPermission(
        permission: Permission,
        permissions: List<String>,
        callback: (Result<Unit>) -> Unit
    ) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                val toRequest = permissions.filter {
                    ContextCompat.checkSelfPermission(
                        requireContext(),
                        it
                    ) != PackageManager.PERMISSION_GRANTED
                }

                if (toRequest.isEmpty()) {
                    callback.invoke(Result.success(Unit))
                    return@repeatOnLifecycle
                }

                permissionCallback?.let {
                    it.callback.invoke(Result.failure(RequestCanceledException(it.permission)))
                    permissionCallback = null
                }

                permissionCallback = PermissionCallback(permission, callback)

                requestPermissionLauncher.launch(toRequest.toTypedArray())
            }
        }
    }

    private class PermissionCallback(
        val permission: Permission,
        val callback: (Result<Unit>) -> Unit
    )
}
