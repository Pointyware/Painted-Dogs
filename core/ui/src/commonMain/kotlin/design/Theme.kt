package org.pointyware.painteddogs.core.ui.design

/**
 * Combines primitives to create a Painted Dogs theme
 */

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 *
 */
@Composable
fun PaintedDogsTheme(
    isDark: Boolean = false,
    content: @Composable ()->Unit,
) {
    MaterialTheme(
        colorScheme = if (isDark) darkColors else lightColors,
        shapes = shapes,
        typography = typography,
        content = content
    )
}

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
