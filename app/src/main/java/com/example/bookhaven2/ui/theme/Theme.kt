package com.example.bookhaven2.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
private val LightColorScheme = lightColorScheme(
    primary = OceanBlue80,
    onPrimary = White,
    primaryContainer = OceanBlue80.copy(alpha = 0.2f),
    onPrimaryContainer = DeepOcean40,

    secondary = Seafoam80,
    onSecondary = Black,
    secondaryContainer = Seafoam80.copy(alpha = 0.2f),
    onSecondaryContainer = MidnightBlue40,

    tertiary = Coral80,
    onTertiary = Black,

    background = Sand80,
    onBackground = DarkGray,

    surface = White,
    onSurface = DarkGray,

    surfaceVariant = LightGray,
    onSurfaceVariant = DarkGray,

    error = Color(0xFFB00020),
    onError = White
)

private val DarkColorScheme = darkColorScheme(
    primary = OceanBlue80,
    onPrimary = Black,
    primaryContainer = DeepOcean40,
    onPrimaryContainer = OceanBlue80,

    secondary = Seafoam80,
    onSecondary = Black,
    secondaryContainer = MidnightBlue40,
    onSecondaryContainer = Seafoam80,

    tertiary = DeepCoral40,
    onTertiary = White,

    background = DeepOcean40,
    onBackground = White,

    surface = MidnightBlue40,
    onSurface = White,

    surfaceVariant = DeepSand40,
    onSurfaceVariant = White,

    error = Color(0xFFCF6679),
    onError = Black
)
@Composable
fun BookHaven2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}