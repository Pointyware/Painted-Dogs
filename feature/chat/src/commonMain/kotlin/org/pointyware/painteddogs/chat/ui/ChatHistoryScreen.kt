package org.pointyware.painteddogs.chat.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryViewModel
import org.pointyware.painteddogs.core.ui.design.LocalGeometry

@Composable
fun ChatHistoryScreen(
    viewModel: ChatHistoryViewModel
) {
    val chatHistory by viewModel.chatList.collectAsState()
    val geometry = LocalGeometry.current
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = geometry.paddingMedium,
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
                ),
                modifier = Modifier.fillMaxWidth()
            )
            HorizontalDivider(
                modifier = Modifier.padding(geometry.paddingSmall),
                thickness = Dp.Hairline,
            )
        }
    }
}
