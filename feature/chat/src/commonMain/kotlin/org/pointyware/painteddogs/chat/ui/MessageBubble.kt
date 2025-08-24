package org.pointyware.painteddogs.chat.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.LocalGeometry

data class MessageBubbleState(
    val content: String,
    val timeStamp: String,
    val author: String
)

@Composable
fun MessageBubble(
    state: MessageBubbleState,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .padding(end = GeometryTokens.dpExtraLarge)
            .clickable(onClick = { expanded = !expanded}),
        horizontalArrangement = Arrangement.Start,
    ) {
        Surface(
            modifier = Modifier,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.primaryContainer,
            shadowElevation = GeometryTokens.dpSmall,
        ) {
            Column(
                modifier = Modifier
                    .padding(LocalGeometry.current.paddingMedium)
            ) {
                Text(
                    text = state.content,
                    modifier = Modifier
                )
                AnimatedVisibility(visible = expanded) {
                    Text(
                        text = state.timeStamp,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth(.1f)
            .weight(1f)
        )
    }
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
