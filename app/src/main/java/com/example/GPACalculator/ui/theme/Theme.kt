package com.example.GPACalculator.ui.theme

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

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC), // Purple, change this
    secondary = Color(0xFF03DAC6), // Teal, change this
    tertiary = Color(0xFFCF6679), // Pink, change this
    background = Color(0xFF121212), // Dark background
    surface = Color(0xFF1E1E1E), // Slightly lighter dark surface
    onPrimary = Color.Black, // Text on primary color
    onSecondary = Color.Black, // Text on secondary color
    onBackground = Color.White, // Text on background
    onSurface = Color.White // Text on surface
)

//Please don't use light mode, it hurt my eyes

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE), // Deep purple (not too bright)
    secondary = Color(0xFF03A9F4), // Light blue (calming)
    tertiary = Color(0xFF018786), // Teal (soft contrast)

    background = Color(0xFFF5F5F5), // Light grayish white (not too bright)
    surface = Color(0xFFFFFFFF), // True white (for card surfaces)

    onPrimary = Color.White, // White text on primary
    onSecondary = Color.Black, // Black text on secondary
    onBackground = Color.Black, // Black text on background
    onSurface = Color.Black // Black text on surfaces
)


@Composable
fun RADGuyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
)
    {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}