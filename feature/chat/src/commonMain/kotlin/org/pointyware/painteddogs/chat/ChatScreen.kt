package org.pointyware.painteddogs.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import org.pointyware.painteddogs.chat.ui.MessageBubble
import org.pointyware.painteddogs.chat.ui.MessageBubbleState
import org.pointyware.painteddogs.chat.viewmodels.ChatUiState
import org.pointyware.painteddogs.chat.viewmodels.ChatViewModel
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.LocalGeometry

@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    navController: NavController
) {
    val chatMessages by viewModel.messages.collectAsState()
    val error by viewModel.error.collectAsState()

    val errorCapture = error
    if (errorCapture != null) {
        Dialog(
            onDismissRequest = { viewModel.onClearError() }
        ) {
            Text(
                text = errorCapture.message ?: errorCapture.toString()
            )
        }
    }
    when (val capture = chatMessages) {
        is ChatUiState.Loading -> {
            Text("Loading")
        }
        is ChatUiState.UnknownChat -> {
            Text("Unknown")
        }
        is ChatUiState.Loaded -> {
            LazyColumn(
                modifier = Modifier,
                reverseLayout = true,
                contentPadding = LocalGeometry.current.paddingSmall,
                verticalArrangement = Arrangement.spacedBy(GeometryTokens.dpSmall),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(capture.messages) { message ->
                    MessageBubble(
                        MessageBubbleState(
                            message.content
                        )
                    )
                }
            }
        }
    }
}
