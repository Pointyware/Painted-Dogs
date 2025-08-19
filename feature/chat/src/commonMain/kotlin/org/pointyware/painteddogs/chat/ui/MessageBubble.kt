package org.pointyware.painteddogs.chat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.LocalGeometry

data class MessageBubbleState(
    val content: String
)

@Composable
fun MessageBubble(
    state: MessageBubbleState,
    modifier: Modifier = Modifier
) {
    Text(
        text = state.content,
        modifier = modifier.padding(LocalGeometry.current.paddingMedium)
    )
}

@Composable
fun Modifier.otherMessage(): Modifier {
    return this.background(
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.shapes.medium)
        .padding(end = GeometryTokens.dpExtraLarge)
}

@Composable
fun Modifier.myMessage(): Modifier {
    return this.background(
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.shapes.medium)
        .padding(start = GeometryTokens.dpExtraLarge)
}
