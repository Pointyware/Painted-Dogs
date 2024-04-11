package org.pointyware.painteddogs.core.ui.design

/**
 * Combines primitives to create a Painted Dogs theme
 */

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 *
 */
@Composable
fun PaintedDogsTheme(
    modifier: Modifier = Modifier,
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
