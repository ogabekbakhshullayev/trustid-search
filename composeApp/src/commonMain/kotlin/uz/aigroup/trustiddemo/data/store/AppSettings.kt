package uz.aigroup.trustiddemo.data.store

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppSettings : KoinComponent {

    private val settings by inject<Settings>()

    fun setAccessToken(accessToken: String?) {
        settings[ACCESS_TOKEN] = accessToken.orEmpty()
    }

    fun getAccessToken(): String {
        return settings.getString(ACCESS_TOKEN, "")
    }

    companion object {
        const val ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
    }
}
