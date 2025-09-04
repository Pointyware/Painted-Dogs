package org.pointyware.painteddogs.core.ui.design

/**
 * Combines primitives to create a Painted Dogs theme
 */

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

/**
 * Extends the Material3 Theme with an [XPDateFormatter].
 */
@Composable
fun PaintedDogsTheme(
    useDynamicColors: Boolean = true,
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable ()->Unit,
) {
    CompositionLocalProvider(
        LocalDateFormat provides SimpleDateFormatter,
        LocalGeometry provides DefaultLocalGeometry
    ) {
        MaterialTheme(
            colorScheme =
                if (useDynamicColors) {
                    dynamicColors(isDark)
                } else { if (isDark) darkColors else lightColors },
            shapes = shapes,
            typography = typography,
            content = content
        )
    }
}

@Composable
fun dynamicColors(isDark: Boolean) = if (isDark) dynamicDarkColors() else dynamicLightColors()
@Composable
expect fun dynamicLightColors(): ColorScheme
@Composable
expect fun dynamicDarkColors(): ColorScheme

@Composable
fun Modifier.paddingSmall(): Modifier {
    return this.padding(LocalGeometry.current.paddingSmall)
}

@Composable
fun Modifier.paddingMedium(): Modifier {
    return this.padding(LocalGeometry.current.paddingMedium)
}

@Composable
fun Modifier.paddingLarge(): Modifier {
    return this.padding(LocalGeometry.current.paddingLarge)
}
