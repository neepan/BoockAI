package com.neepan.boockai.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = BoockPrimary,
    onPrimary = BoockOnPrimary,
    primaryContainer = BoockPrimaryLight,
    secondary = BoockSecondary,
    background = BoockBackground,
    surface = BoockSurface,
    surfaceVariant = BoockSurfaceVariant,
    onBackground = BoockOnSurface,
    onSurface = BoockOnSurface,
    onSurfaceVariant = BoockOnSurfaceVariant,
    outline = BoockOutline,
    outlineVariant = BoockOutlineVariant
)

private val DarkColorScheme = darkColorScheme(
    primary = ReaderDarkAccent,
    onPrimary = DarkBackground,
    primaryContainer = BoockPrimaryActive,
    secondary = DarkOnSurfaceVariant,
    background = DarkBackground,
    surface = DarkSurface,
    surfaceVariant = ReaderDarkCardSurface,
    onBackground = DarkOnSurface,
    onSurface = DarkOnSurface,
    onSurfaceVariant = DarkOnSurfaceVariant,
    outline = BoockOutline,
    outlineVariant = BoockOutlineVariant
)

@Composable
fun BoockTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = BoockTypography,
        content = content
    )
}
