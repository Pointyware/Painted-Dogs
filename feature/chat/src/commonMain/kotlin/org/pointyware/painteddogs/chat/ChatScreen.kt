package org.pointyware.painteddogs.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.chat.ui.ChatView
import org.pointyware.painteddogs.chat.viewmodels.ChatViewModel

@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
) {
    val chatMessages by viewModel.messages.collectAsState()
    val error by viewModel.error.collectAsState()

    ChatView(
        state = chatMessages,
        error = error,
        modifier = Modifier.fillMaxSize(),
        onSendMessage = { viewModel.onSendMessage(it) },
        onClearError = { viewModel.onClearError() }
    )
}
