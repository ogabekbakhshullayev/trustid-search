package uz.softonic.eld.permissions.ios

import platform.Photos.PHAuthorizationStatus
import platform.Photos.PHAuthorizationStatusAuthorized
import platform.Photos.PHAuthorizationStatusDenied
import platform.Photos.PHAuthorizationStatusNotDetermined
import platform.Photos.PHPhotoLibrary
import uz.softonic.eld.permissions.DeniedAlwaysException
import uz.softonic.eld.permissions.Permission
import uz.softonic.eld.permissions.PermissionState
import uz.softonic.eld.permissions.mainContinuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class GalleryPermissionDelegate : PermissionDelegate {
    override suspend fun providePermission(): Result<Unit> {
        return providePermission(PHPhotoLibrary.authorizationStatus())
    }

    private suspend fun providePermission(status: PHAuthorizationStatus): Result<Unit> {
        return when (status) {
            PHAuthorizationStatusAuthorized -> Result.success(Unit)
            PHAuthorizationStatusNotDetermined -> {
                val newStatus = suspendCoroutine<PHAuthorizationStatus> { continuation ->
                    requestGalleryAccess { continuation.resume(it) }
                }
                providePermission(newStatus)
            }

            PHAuthorizationStatusDenied -> {
                Result.failure(DeniedAlwaysException(Permission.GALLERY))
            }

            else -> {
                Result.failure(Throwable("unknown gallery authorization status $status"))
            }
        }
    }

    override suspend fun getPermissionState(): PermissionState {
        return when (val status: PHAuthorizationStatus = PHPhotoLibrary.authorizationStatus()) {
            PHAuthorizationStatusAuthorized -> PermissionState.Granted
            PHAuthorizationStatusNotDetermined -> PermissionState.NotDetermined
            PHAuthorizationStatusDenied -> PermissionState.DeniedAlways
            else -> error("unknown gallery authorization status $status")
        }
    }
}

private fun requestGalleryAccess(callback: (PHAuthorizationStatus) -> Unit) {
    PHPhotoLibrary.requestAuthorization(mainContinuation { status: PHAuthorizationStatus ->
        callback(status)
    })
}
