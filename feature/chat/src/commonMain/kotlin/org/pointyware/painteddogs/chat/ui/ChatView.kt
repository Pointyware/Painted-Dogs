package org.pointyware.painteddogs.chat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import org.pointyware.painteddogs.chat.viewmodels.ChatUiState
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.LocalGeometry
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun ChatView(
    state: ChatUiState,
    error: Throwable?,
    modifier: Modifier = Modifier,
    onClearError: ()->Unit,
) {

    if (error != null) {
        Dialog(
            onDismissRequest = onClearError
        ) {
            Text(
                text = error.message ?: error.toString()
            )
        }
    }
    when (state) {
        is ChatUiState.Loading -> {
            Text("Loading")
        }
        is ChatUiState.UnknownChat -> {
            Text("Unknown")
        }
        is ChatUiState.Loaded -> {
            LazyColumn(
                modifier = modifier,
                reverseLayout = true,
                contentPadding = LocalGeometry.current.paddingSmall,
                verticalArrangement = Arrangement.spacedBy(GeometryTokens.dpMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(state.messages) { message ->
                    ChatLogMessage(
                        state = ChatLogElementState.Message(
                            content = message.content,
                            timeStamp = message.timeSent.toString(),
                            isSender = false
                        )
                    )
                }
            }
        }
    }
}
