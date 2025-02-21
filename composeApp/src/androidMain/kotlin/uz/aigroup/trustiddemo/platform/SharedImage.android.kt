package uz.aigroup.trustiddemo.platform

import android.graphics.Bitmap
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SharedImage(private val bitmap: Bitmap) {

    actual suspend fun toBase64(): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val bytes = byteArrayOutputStream.toByteArray()

        return withContext(Dispatchers.IO) {
            "data:image/jpeg;base64,${
                Base64.encodeToString(bytes, Base64.DEFAULT)
            }"
        }
    }

    actual fun toImageBitmap(): ImageBitmap {
        return bitmap.asImageBitmap()
    }
}