package org.pointyware.painteddogs.core.ui.design

/**
 * Combines primitives to create a Painted Dogs theme
 */

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
interface XPDateFormatter {
    fun format(date: Instant): String
}
@OptIn(ExperimentalTime::class)
val SimpleDateFormatter = object : XPDateFormatter {
    override fun format(date: Instant): String {
        return date.toString()
    }
}
val LocalDateFormat = compositionLocalOf<XPDateFormatter> { throw IllegalStateException("DateFormat not provided") }

/**
 * Extends the Material3 Theme with an [XPDateFormatter].
 */
@Composable
fun PaintedDogsTheme(
    isDark: Boolean = false,
    content: @Composable ()->Unit,
) {
    CompositionLocalProvider(
        LocalDateFormat provides SimpleDateFormatter
    ) {
        MaterialTheme(
            colorScheme = if (isDark) darkColors else lightColors,
            shapes = shapes,
            typography = typography,
            content = content
        )
    }
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
