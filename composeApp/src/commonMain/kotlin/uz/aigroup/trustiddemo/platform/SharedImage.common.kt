package uz.aigroup.trustiddemo.platform

import androidx.compose.ui.graphics.ImageBitmap

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class SharedImage {

    suspend fun toBase64(): String

    fun toImageBitmap(): ImageBitmap
}