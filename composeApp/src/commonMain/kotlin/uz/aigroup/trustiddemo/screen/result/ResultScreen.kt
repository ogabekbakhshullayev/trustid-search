package uz.aigroup.trustiddemo.screen.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import trustid_search.composeapp.generated.resources.Res
import uz.aigroup.trustiddemo.screen.home.HomeScreen
import uz.aigroup.trustiddemo.ui.designsystem.component.AppBackground
import uz.aigroup.trustiddemo.ui.designsystem.component.AppFilledButton

object ResultScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = navigator.rememberNavigatorScreenModel { ResultScreenModel() }
        val state by screenModel.state.collectAsState()

        LifecycleEffect(onStarted = { screenModel.onEvent(ResultEvent.Idle) })

        AppBackground {
            when {
                state.loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding(),
                        contentAlignment = Alignment.Center,
                    ) {
                        val composition by rememberLottieComposition {
                            LottieCompositionSpec.JsonString(
                                Res.readBytes("files/loading.json").decodeToString()
                            )
                        }

                        Image(
                            modifier = Modifier.fillMaxSize(),
                            contentDescription = "Loading animation",
                            painter = rememberLottiePainter(
                                composition = composition, iterations = Compottie.IterateForever
                            ),
                        )
                    }
                }

                !state.errorMessage.isNullOrEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            val composition by rememberLottieComposition {
                                LottieCompositionSpec.JsonString(
                                    Res.readBytes("files/error.json").decodeToString()
                                )
                            }

                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f),
                                contentDescription = "Error animation",
                                painter = rememberLottiePainter(
                                    composition = composition, iterations = Compottie.IterateForever
                                ),
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = state.errorMessage.orEmpty(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium,
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        AppFilledButton(
                            text = "Finish",
                            onClick = {
                                screenModel.onEvent(ResultEvent.Idle)
                                navigator.replaceAll(HomeScreen)
                            },
                        )
                    }
                }

//                !state.result.isNullOrEmpty() -> {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .safeDrawingPadding()
//                            .padding(24.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
//                    ) {
//                        Box(
//                            contentAlignment = Alignment.Center
//                        ) {
//                            val composition by rememberLottieComposition {
//                                LottieCompositionSpec.JsonString(
//                                    Res.readBytes("files/success.json").decodeToString()
//                                )
//                            }
//
//                            Image(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .aspectRatio(1f),
//                                contentDescription = "Success animation",
//                                painter = rememberLottiePainter(
//                                    composition = composition,
//                                    iterations = 1
//                                ),
//                            )
//                        }
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        Text(
//                            text = state.result.orEmpty(),
//                            fontSize = 24.sp,
//                            fontWeight = FontWeight.Medium,
//                        )
//
//                        Spacer(modifier = Modifier.height(24.dp))
//
//                        AppFilledButton(
//                            text = "Finish",
//                            onClick = {
//                                screenModel.onEvent(HomeEvent.Idle)
//                                navigator.replaceAll(HomeScreen)
//                            },
//                        )
//                    }
//                }

                else -> {
                }
            }
        }
    }
}



