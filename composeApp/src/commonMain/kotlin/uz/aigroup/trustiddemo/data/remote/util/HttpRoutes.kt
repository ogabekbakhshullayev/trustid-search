package uz.aigroup.trustiddemo.data.remote.util

object HttpRoutes {
    private const val API_URL = "https://spa.aigroup.uz"

    private val getApiUrl
        get() = API_URL

    val accessToken
        get() = "$getApiUrl/auth/access-token"

    val search
        get() = "$getApiUrl/api/1-to-n/search/"

    val task
        get() = "$getApiUrl/api/1-to-n/search/"
}
