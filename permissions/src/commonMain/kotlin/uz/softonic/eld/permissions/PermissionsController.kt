package uz.softonic.eld.permissions

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect interface PermissionsController {
    /**
     * Check is permission already granted and if not - request permission from user.
     *
     * @param permission what permission we want to provide
     *
     * @throws DeniedException if user decline request, but we can retry (only on Android)
     * @throws DeniedAlwaysException if user decline request and we can't show request again
     *  (we should send user to settings)
     * @throws RequestCanceledException if user cancel request without response (only on Android)
     */
    suspend fun providePermission(permission: Permission): Result<Unit>

    /**
     * @return true if permission already granted. In all other cases - false.
     */
    suspend fun isPermissionGranted(permission: Permission): Boolean

    /**
     * Returns current state of permission. Can be suspended because on
     * android detection of Denied/NotDetermined case require binded FragmentManager.
     *
     * @param permission state of what permission we want
     *
     * @return current state. On Android can't be DeniedAlways (except push notifications).
     * On iOS can't be Denied.
     */
    suspend fun getPermissionState(permission: Permission): PermissionState

    /**
     * Open system UI of application settings to change permissions state
     */
    fun openAppSettings()
}
