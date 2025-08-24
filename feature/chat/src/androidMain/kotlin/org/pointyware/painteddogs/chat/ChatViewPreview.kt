package org.pointyware.painteddogs.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.painteddogs.chat.interactors.ChatLogItem
import org.pointyware.painteddogs.chat.ui.ChatView
import org.pointyware.painteddogs.chat.viewmodels.ChatUiState
import org.pointyware.painteddogs.chat.viewmodels.ContactDummyData
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import kotlin.time.Clock
import kotlin.time.Duration.Companion.hours
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun ChatViewPreview() {
    PaintedDogsTheme {
        ChatView(
            state = ChatUiState.Loaded(
                elements = listOf(
                    ChatLogItem.TimeDivider(
                        Clock.System.now() - 1.hours
                    ),
                    ChatLogItem.Author(
                        contact = ContactDummyData.userLink
                    ),
                    ChatLogItem.Message(
                        content = "Hi, how are you doing?",
                        timeStamp = Clock.System.now() - 1.hours,
                        isSender = false
                    ),
                    ChatLogItem.Message(
                        isSender = true,
                        content = "Good",
                        timeStamp = Clock.System.now() - .5.hours
                    ),
                    ChatLogItem.Message(
                        isSender = true,
                        content = "How are you?",
                        timeStamp = Clock.System.now() - .25.hours
                    ),
                    ChatLogItem.Author(
                        contact = ContactDummyData.userLink
                    ),
                    ChatLogItem.Message(
                        isSender = false,
                        content = "That's good to hear! I'm just reaching out to talk to you about your cars extended warranty!",
                        timeStamp = Clock.System.now() - .25.hours
                    )
                )
            ),
            error = null,
            modifier = Modifier.fillMaxSize(),
            onClearError = { }
        )
    }
}
