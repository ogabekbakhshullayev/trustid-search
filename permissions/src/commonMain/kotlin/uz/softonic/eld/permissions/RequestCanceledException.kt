package uz.softonic.eld.permissions

class RequestCanceledException(
    val permission: Permission,
    message: String? = null
) : Exception(message)
