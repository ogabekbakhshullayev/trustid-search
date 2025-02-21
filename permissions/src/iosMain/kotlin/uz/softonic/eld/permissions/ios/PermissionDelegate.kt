package uz.softonic.eld.permissions.ios

import uz.softonic.eld.permissions.PermissionState

internal interface PermissionDelegate {
    suspend fun providePermission(): Result<Unit>

    suspend fun getPermissionState(): PermissionState
}
