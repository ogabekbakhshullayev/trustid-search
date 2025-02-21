package uz.aigroup.trustiddemo.data.store

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.aigroup.trustiddemo.data.remote.response.AccessTokenResponse

class AppSettings : KoinComponent {

    private val settings by inject<Settings>()

    fun getAccessToken(): String {
        return settings.getString(ACCESS_TOKEN, "")
    }

    fun getRefreshToken(): String {
        return settings.getString(REFRESH_TOKEN, "")
    }

    fun setTokens(response: AccessTokenResponse?) {
        settings[ACCESS_TOKEN] = response?.accessToken.orEmpty()
    }

    fun setAccessToken(accessToken: String?) {
        settings[ACCESS_TOKEN] = accessToken.orEmpty()
    }

    companion object {
        const val ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
        const val REFRESH_TOKEN = "KEY_REFRESH_TOKEN"
    }
}
