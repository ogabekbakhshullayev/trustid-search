package uz.aigroup.trustiddemo

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.aigroup.trustiddemo.data.store.AppSettings
import uz.aigroup.trustiddemo.di.initKoin
import uz.aigroup.trustiddemo.screen.app.AppContent

class App : Application() {

    companion object {
        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        initKoin {
            androidContext(this@App)
        }
    }
}

class AppActivity : FragmentActivity(), KoinComponent {

    private val appSettings by inject<AppSettings>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val onboarding = appSettings.getAccessToken().isEmpty()

        setContent { AppContent(onboarding) }

        requestMicrophonePermission()
    }

    private fun requestMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                1000
            )
        }
    }
}