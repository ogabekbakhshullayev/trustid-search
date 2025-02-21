package uz.softonic.eld.permissions.ios

import uz.softonic.eld.permissions.PermissionState

internal class AlwaysGrantedPermissionDelegate : PermissionDelegate {

    override suspend fun providePermission(): Result<Unit> = Result.success(Unit)

    override suspend fun getPermissionState(): PermissionState = PermissionState.Granted
}
