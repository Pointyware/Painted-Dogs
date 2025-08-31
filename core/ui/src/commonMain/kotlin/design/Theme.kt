package org.pointyware.painteddogs.core.ui.design

/**
 * Combines primitives to create a Painted Dogs theme
 */

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.jetbrains.compose.ui.tooling.preview.Preview

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

@Preview
@Composable
fun PaintedDogsThemePreview() {
    PaintedDogsTheme {
        Surface {
            Text("ooh, a button")
            Button(onClick = { println("Click") }) {
                Text("Click me!")
            }
        }
    }
}
