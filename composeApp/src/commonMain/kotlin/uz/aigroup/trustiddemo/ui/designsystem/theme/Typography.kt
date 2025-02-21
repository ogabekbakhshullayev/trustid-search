package uz.aigroup.trustiddemo.ui.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import trustid_search.composeapp.generated.resources.Res
import trustid_search.composeapp.generated.resources.bold
import trustid_search.composeapp.generated.resources.medium
import trustid_search.composeapp.generated.resources.regular

@Composable
private fun fontFamily(): FontFamily {
    return FontFamily(
        Font(Res.font.regular, FontWeight.Light),
        Font(Res.font.regular, FontWeight.Normal),
        Font(Res.font.medium, FontWeight.Medium),
        Font(Res.font.bold, FontWeight.SemiBold),
        Font(Res.font.bold, FontWeight.Bold),
    )
}

internal val Typography
    @Composable
    get() = Typography(
        headlineLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily(),
            fontSize = 30.sp
        ),
        headlineMedium = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily(),
            fontSize = 26.sp
        ),
        headlineSmall = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily(),
            fontSize = 22.sp
        ),
        titleLarge = TextStyle(
            fontWeight = FontWeight.Medium,
            fontFamily = fontFamily(),
            fontSize = 20.sp
        ),
        titleMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontFamily = fontFamily(),
            fontSize = 18.sp,
            letterSpacing = 0.1.sp
        ),
        titleSmall = TextStyle(
            fontWeight = FontWeight.Medium,
            fontFamily = fontFamily(),
            fontSize = 14.sp,
            letterSpacing = 0.1.sp
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = fontFamily(),
            fontSize = 16.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = fontFamily(),
            fontSize = 14.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = fontFamily(),
            fontSize = 12.sp,
            letterSpacing = 0.4.sp
        ),
        labelLarge = TextStyle(
            fontWeight = FontWeight.Light,
            fontFamily = fontFamily(),
            fontSize = 14.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontWeight = FontWeight.Light,
            fontFamily = fontFamily(),
            fontSize = 12.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontWeight = FontWeight.Light,
            fontFamily = fontFamily(),
            fontSize = 10.sp
        )
    )