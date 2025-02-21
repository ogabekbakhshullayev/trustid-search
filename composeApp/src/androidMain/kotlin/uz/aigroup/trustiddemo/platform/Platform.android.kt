package uz.aigroup.trustiddemo.platform

import android.widget.Toast
import uz.aigroup.trustiddemo.App
import java.util.UUID

actual val platform = Platform.Android

actual fun randomUUID(): String {
    return UUID.randomUUID().toString()
}

actual fun toast(message: String?) {
    Toast.makeText(App.INSTANCE, message, Toast.LENGTH_LONG).show()
}
