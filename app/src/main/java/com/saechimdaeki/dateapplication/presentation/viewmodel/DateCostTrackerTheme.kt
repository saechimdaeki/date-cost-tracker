package com.saechimdaeki.dateapplication.presentation.viewmodel

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.saechimdaeki.dateapplication.ui.theme.md_theme_dark_background
import com.saechimdaeki.dateapplication.ui.theme.md_theme_dark_primary
import com.saechimdaeki.dateapplication.ui.theme.md_theme_dark_secondary
import com.saechimdaeki.dateapplication.ui.theme.md_theme_dark_surface
import com.saechimdaeki.dateapplication.ui.theme.md_theme_light_background
import com.saechimdaeki.dateapplication.ui.theme.md_theme_light_primary
import com.saechimdaeki.dateapplication.ui.theme.md_theme_light_secondary
import com.saechimdaeki.dateapplication.ui.theme.md_theme_light_surface
import com.saechimdaeki.dateapplication.ui.theme.Typography
private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    secondary = md_theme_light_secondary,
    background = md_theme_light_background,
    surface = md_theme_light_surface,
)

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    secondary = md_theme_dark_secondary,
    background = md_theme_dark_background,
    surface = md_theme_dark_surface,
)

@Composable
fun DateCostTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}