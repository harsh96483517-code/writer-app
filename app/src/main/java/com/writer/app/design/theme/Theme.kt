package com.writer.app.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush

enum class ThemeMode { LIGHT, DARK, SYSTEM }

/** Extra glass-specific colors that Material3's ColorScheme does not carry. */
data class GlassColors(
    val background: Color,
    val backgroundSecondary: Color,
    val glassSurface: Color,
    val glassSurfaceElevated: Color,
    val glassBorder: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textMuted: Color,
    val accentGradient: Brush
)

val LocalGlassColors = staticCompositionLocalOf<GlassColors> {
    error("GlassColors not provided")
}

private val darkGlass = GlassColors(
    background = DarkBackground,
    backgroundSecondary = DarkBackgroundSecondary,
    glassSurface = GlassSurfaceDark,
    glassSurfaceElevated = GlassSurfaceElevatedDark,
    glassBorder = GlassBorderDark,
    textPrimary = TextPrimaryDark,
    textSecondary = TextSecondaryDark,
    textMuted = TextMutedDark,
    accentGradient = Brush.linearGradient(listOf(PrimaryBlue, Purple))
)

private val lightGlass = GlassColors(
    background = LightBackground,
    backgroundSecondary = Color(0xFFEAEEF8),
    glassSurface = GlassSurfaceLight,
    glassSurfaceElevated = GlassSurfaceElevatedLight,
    glassBorder = GlassBorderLight,
    textPrimary = TextPrimaryLight,
    textSecondary = TextSecondaryLight,
    textMuted = TextMutedLight,
    accentGradient = Brush.linearGradient(listOf(PrimaryBlue, Purple))
)

private val DarkScheme = darkColorScheme(
    primary = PrimaryBlue,
    secondary = Purple,
    tertiary = ElectricBlue,
    background = DarkBackground,
    surface = DarkBackgroundSecondary,
    onPrimary = Color.White,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    error = ErrorRed
)

private val LightScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = Purple,
    tertiary = ElectricBlue,
    background = LightBackground,
    surface = Color.White,
    onPrimary = Color.White,
    onBackground = TextPrimaryLight,
    onSurface = TextPrimaryLight,
    error = ErrorRed
)

@Composable
fun WriterTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    content: @Composable () -> Unit
) {
    val dark = when (themeMode) {
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
    }
    val glass = if (dark) darkGlass else lightGlass
    MaterialTheme(
        colorScheme = if (dark) DarkScheme else LightScheme,
        typography = WriterTypography,
        shapes = WriterShapes
    ) {
        CompositionLocalProvider(LocalGlassColors provides glass, content = content)
    }
}

/** Convenience accessor inside composables. */
object WriterTheme {
    val colors: GlassColors
        @Composable get() = LocalGlassColors.current
}
