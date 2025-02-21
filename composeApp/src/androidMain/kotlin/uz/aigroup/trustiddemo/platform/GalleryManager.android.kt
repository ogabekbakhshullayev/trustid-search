package uz.aigroup.trustiddemo.platform

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun rememberGalleryManager(onResult: (SharedImage) -> Unit): GalleryManager {
    val context = LocalContext.current
    val contentResolver = context.contentResolver
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                ImageHandler.retrieveImageFromUri(uri, contentResolver).let { bitmap ->
                    onResult.invoke(SharedImage(bitmap))
                }
            }
        }
    return remember {
        GalleryManager(onLaunch = {
            galleryLauncher.launch(
                PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        })
    }
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class GalleryManager actual constructor(private val onLaunch: () -> Unit) {

    actual fun launch() {
        onLaunch()
    }
}