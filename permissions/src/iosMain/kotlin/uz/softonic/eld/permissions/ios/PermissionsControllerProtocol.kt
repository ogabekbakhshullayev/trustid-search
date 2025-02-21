package uz.softonic.eld.permissions.ios

import uz.softonic.eld.permissions.Permission
import uz.softonic.eld.permissions.PermissionState

interface PermissionsControllerProtocol {
    suspend fun providePermission(permission: Permission): Result<Unit>
    suspend fun isPermissionGranted(permission: Permission): Boolean
    suspend fun getPermissionState(permission: Permission): PermissionState
    fun openAppSettings()
}
