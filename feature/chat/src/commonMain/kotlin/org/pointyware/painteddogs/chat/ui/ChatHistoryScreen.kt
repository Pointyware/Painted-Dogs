package org.pointyware.painteddogs.chat.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryViewModel

@Composable
fun ChatHistoryScreen(
    viewModel: ChatHistoryViewModel
) {
    val chatHistory by viewModel.chatList.collectAsState()
    LazyColumn(
        modifier = Modifier
    ) {
        items(chatHistory) { chatRecord ->
            ChatRowView(
                state = ChatRowViewState(
                    participants = chatRecord.participants.map { participant ->
                        ParticipantViewState(
                            label = participant.label,
                            image = ParticipantImage.NoProfileImage // TODO: get from entity and load image if necessary
                        )
                    },
                    excerpt = chatRecord.excerpt,
                )
            )
        }
    }
}
