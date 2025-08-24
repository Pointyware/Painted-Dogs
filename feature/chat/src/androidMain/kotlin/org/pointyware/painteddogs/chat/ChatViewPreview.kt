package org.pointyware.painteddogs.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.painteddogs.chat.data.ChatMessage
import org.pointyware.painteddogs.chat.ui.ChatView
import org.pointyware.painteddogs.chat.viewmodels.ChatUiState
import org.pointyware.painteddogs.chat.viewmodels.ContactDummyData
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun ChatViewPreview() {
    PaintedDogsTheme {
        ChatView(
            state = ChatUiState.Loaded(
                chatId = "id",
                messages = listOf(
                    ChatMessage(
                        id = "0",
                        sender = ContactDummyData.userLink,
                        content = "TODO()",
                        timeSent = Clock.System.now()
                    )
                )
            ),
            error = null,
            modifier = Modifier.fillMaxSize(),
            onClearError = { }
        )
    }
}
