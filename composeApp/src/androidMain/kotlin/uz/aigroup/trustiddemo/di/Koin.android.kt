package uz.aigroup.trustiddemo.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module
import uz.aigroup.trustiddemo.platform.configureKtor

actual fun platformModule() = module {
    single { createSettings(get()) }
    single {
        HttpClient(OkHttp) {
            configureKtor(appSettings = get())

            engine {
                addInterceptor(createChuckerInterceptor(get()))
            }
        }
    }
}

private fun createSettings(context: Context): Settings {
    val delegate = context.getSharedPreferences("trustid.preferences", Context.MODE_PRIVATE)
    return SharedPreferencesSettings(delegate = delegate)
}

private fun createChuckerInterceptor(context: Context): ChuckerInterceptor {
    val chuckerCollector = ChuckerCollector(
        context = context,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )

    return ChuckerInterceptor.Builder(context)
        .collector(chuckerCollector)
        .maxContentLength(10_000L)
        .alwaysReadResponseBody(true)
        .build()
}
