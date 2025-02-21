package uz.softonic.eld.permissions.ios

import platform.AVFoundation.AVAuthorizationStatus
import platform.AVFoundation.AVAuthorizationStatusAuthorized
import platform.AVFoundation.AVAuthorizationStatusDenied
import platform.AVFoundation.AVAuthorizationStatusNotDetermined
import platform.AVFoundation.AVAuthorizationStatusRestricted
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVMediaType
import platform.AVFoundation.authorizationStatusForMediaType
import platform.AVFoundation.requestAccessForMediaType
import uz.softonic.eld.permissions.DeniedAlwaysException
import uz.softonic.eld.permissions.Permission
import uz.softonic.eld.permissions.PermissionState
import uz.softonic.eld.permissions.mainContinuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class AVCapturePermissionDelegate(
    private val type: AVMediaType,
    private val permission: Permission
) : PermissionDelegate {

    override suspend fun providePermission(): Result<Unit> {
        return when (val status: AVAuthorizationStatus = currentAuthorizationStatus()) {
            AVAuthorizationStatusAuthorized -> Result.success(Unit)
            AVAuthorizationStatusNotDetermined -> {
                val isGranted: Boolean = suspendCoroutine { continuation ->
                    AVCaptureDevice.requestAccess(type) { continuation.resume(it) }
                }
                if (isGranted) Result.success(Unit)
                else Result.failure(DeniedAlwaysException(permission))
            }

            AVAuthorizationStatusDenied -> {
                Result.failure(DeniedAlwaysException(permission))
            }

            else -> {
                Result.failure(Throwable("unknown authorization status $status"))
            }
        }
    }

    override suspend fun getPermissionState(): PermissionState {
        return when (val status: AVAuthorizationStatus = currentAuthorizationStatus()) {
            AVAuthorizationStatusAuthorized -> PermissionState.Granted
            AVAuthorizationStatusNotDetermined -> PermissionState.NotDetermined
            AVAuthorizationStatusDenied -> PermissionState.DeniedAlways
            AVAuthorizationStatusRestricted -> PermissionState.Granted
            else -> error("unknown authorization status $status")
        }
    }

    private fun currentAuthorizationStatus(): AVAuthorizationStatus {
        return AVCaptureDevice.authorizationStatusForMediaType(type)
    }
}

private fun AVCaptureDevice.Companion.requestAccess(
    type: AVMediaType,
    callback: (isGranted: Boolean) -> Unit
) {
    this.requestAccessForMediaType(type, mainContinuation { isGranted: Boolean ->
        callback(isGranted)
    })
}
