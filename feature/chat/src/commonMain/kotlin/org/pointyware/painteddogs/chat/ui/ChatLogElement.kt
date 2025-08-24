package org.pointyware.painteddogs.chat.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.LocalGeometry
import kotlin.time.ExperimentalTime

sealed interface ChatLogElementState {
    /**
     * A time divider is inserted at the beginning of each day.
     */
    @OptIn(ExperimentalTime::class)
    class TimeDivider(val day: String): ChatLogElementState

    /**
     * An author indicator is inserted before the first message of a given author.
     */
    class Author(val username: String): ChatLogElementState

    /**
     * A message displays the content and specific time of an entry in the chat log.
     */
    class Message(
        val content: String,
        val timeStamp: String,
        val isSender: Boolean
    ): ChatLogElementState
}

@Composable
fun ChatLogElement(
    state: ChatLogElementState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is ChatLogElementState.TimeDivider -> {
            ChatLogTimeDivider(
                state,
                modifier = modifier,
            )
        }
        is ChatLogElementState.Author -> {
            ChatLogAuthor(state)
        }
        is ChatLogElementState.Message -> {
            ChatLogMessage(
                state,
                modifier = Modifier
            )
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun ChatLogTimeDivider(
    state: ChatLogElementState.TimeDivider,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = state.day)
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ChatLogAuthor(
    state: ChatLogElementState.Author,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(GeometryTokens.dpMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = state.username)
        HorizontalDivider(
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ChatLogMessage(
    state: ChatLogElementState.Message,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val arrangement = remember(state.isSender) {
        if (state.isSender) {
            Arrangement.End
        } else {
            Arrangement.Start
        }
    }
    val background = if (state.isSender) {
        MaterialTheme.colorScheme.secondaryContainer
    } else {
        MaterialTheme.colorScheme.tertiaryContainer
    }
    val paddingMod = if (state.isSender) {
        Modifier.padding(start = GeometryTokens.dpExtraLarge)
    } else {
        Modifier.padding(end = GeometryTokens.dpExtraLarge)
    }
    Row(
        modifier = modifier
            .then(paddingMod)
            .clickable(onClick = { expanded = !expanded}),
        horizontalArrangement = arrangement,
    ) {
        if (state.isSender) {
            Spacer(modifier = Modifier
                .fillMaxWidth(.1f)
                .weight(1f)
            )
        }
        Surface(
            modifier = Modifier,
            shape = MaterialTheme.shapes.medium,
            color = background,
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
        if (!state.isSender) {
            Spacer(modifier = Modifier
                .fillMaxWidth(.1f)
                .weight(1f)
            )
        }
    }
}
