package uz.aigroup.trustiddemo.platform

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

//@Composable
//actual fun rememberCameraManager(onResult: (SharedImage) -> Unit): CameraManager {
//    val cameraLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.TakePicturePreview(),
//        onResult = { bitmap ->
//            bitmap?.let { onResult.invoke(SharedImage(bitmap)) }
//        }
//    )
//    return remember {
//        CameraManager(
//            onLaunch = {
//                cameraLauncher.launch(null)
//            }
//        )
//    }
//}

@Composable
actual fun rememberCameraManager(onResult: (SharedImage) -> Unit): CameraManager {
    val context = LocalContext.current
    val contentResolver = context.contentResolver
    var tempPhotoUri by remember { mutableStateOf(value = Uri.EMPTY) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                onResult.invoke(
                    SharedImage(
                        ImageHandler.retrieveImageFromUri(
                            tempPhotoUri,
                            contentResolver
                        )
                    )
                )
            }
        }
    )
    return remember {
        CameraManager(
            onLaunch = {
                tempPhotoUri = ComposeFileProvider.getImageUri(context)
                cameraLauncher.launch(tempPhotoUri)
            }
        )
    }
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class CameraManager actual constructor(private val onLaunch: () -> Unit) {
    actual fun launch() {
        onLaunch()
    }
}