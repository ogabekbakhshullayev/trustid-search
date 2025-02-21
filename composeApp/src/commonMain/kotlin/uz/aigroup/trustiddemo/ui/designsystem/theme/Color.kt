package uz.aigroup.trustiddemo.ui.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ShadowColor
    @Composable
    get() = MaterialTheme.colorScheme.outline.copy(alpha = .5f)

val DividerColor
    @Composable
    get() = MaterialTheme.colorScheme.outline.copy(alpha = .2f)

val Seed = Color(0xFF254053)

val WindowBackground = Color(0xFFf7f8f3)
val ColorTMobile = Color(0xFFFE0274)

val md_theme_light_primary = Color(0xFF439D4D)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFF9AF89C)
val md_theme_light_onPrimaryContainer = Color(0xFF002106)
val md_theme_light_secondary = Color(0xFF526350)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFD5E8CF)
val md_theme_light_onSecondaryContainer = Color(0xFF101F10)
val md_theme_light_tertiary = Color(0xFF39656B)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFBCEBF1)
val md_theme_light_onTertiaryContainer = Color(0xFF001F23)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFCFDF7)
val md_theme_light_onBackground = Color(0xFF1A1C19)
val md_theme_light_surface = Color(0xFFFCFDF7)
val md_theme_light_onSurface = Color(0xFF1A1C19)
val md_theme_light_surfaceVariant = Color(0xFFDEE5D9)
val md_theme_light_onSurfaceVariant = Color(0xFF424940)
val md_theme_light_outline = Color(0xFF72796F)
val md_theme_light_inverseOnSurface = Color(0xFFF0F1EB)
val md_theme_light_inverseSurface = Color(0xFF2F312D)
val md_theme_light_inversePrimary = Color(0xFF7FDB83)
val md_theme_light_surfaceTint = Color(0xFF046E24)
val md_theme_light_outlineVariant = Color(0xFFC2C9BD)
val md_theme_light_scrim = Color(0xFF000000)

val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)

val LightBackgroundTheme = BackgroundTheme(color = md_theme_light_background)