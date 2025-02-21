package uz.aigroup.trustiddemo.platform

enum class Platform {
    Android,
    IOS,
}

expect val platform: Platform

expect fun randomUUID(): String

expect fun toast(message: String?)