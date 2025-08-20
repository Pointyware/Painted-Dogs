package org.pointyware.painteddogs.core.ui.org.pointyware.painteddogs.ui.design

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TypographyPreview() {
    val meanings = listOf(
        "Display",
        "Headline",
        "Title",
        "Body",
        "Label"
    )
    val sizes = listOf(
        "Large",
        "Medium",
        "Small",
    )

    Column {
        sizes.forEach { size ->
            meanings.forEach { meaning ->
                val textStyle = when (size) {
                    "Large" -> when (meaning) {
                        "Display" -> MaterialTheme.typography.displayLarge
                        "Headline" -> MaterialTheme.typography.headlineLarge
                        "Title" -> MaterialTheme.typography.titleLarge
                        "Body" -> MaterialTheme.typography.bodyLarge
                        else -> // Label
                            MaterialTheme.typography.labelLarge
                    }
                    "Medium" -> when (meaning) {
                        "Display" -> MaterialTheme.typography.displayMedium
                        "Headline" -> MaterialTheme.typography.headlineMedium
                        "Title" -> MaterialTheme.typography.titleMedium
                        "Body" -> MaterialTheme.typography.bodyMedium
                        else -> // Label
                            MaterialTheme.typography.labelMedium
                    }
                    else -> // Small
                        when (meaning) {
                            "Display" -> MaterialTheme.typography.displaySmall
                            "Headline" -> MaterialTheme.typography.headlineSmall
                            "Title" -> MaterialTheme.typography.titleSmall
                            "Body" -> MaterialTheme.typography.bodySmall
                            else -> // Label
                                MaterialTheme.typography.labelSmall
                        }
                }
                Text(
                    text = "$size $meaning",
                    style = textStyle
                )
            }
        }
    }
}
