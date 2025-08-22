package org.pointyware.painteddogs.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import org.pointyware.painteddogs.chat.navigation.Chat
import org.pointyware.painteddogs.chat.navigation.NewChat
import org.pointyware.painteddogs.chat.ui.ChatRowView
import org.pointyware.painteddogs.chat.ui.ChatRowViewState
import org.pointyware.painteddogs.chat.ui.ParticipantImage
import org.pointyware.painteddogs.chat.ui.ParticipantViewState
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryViewModel
import org.pointyware.painteddogs.core.ui.design.LocalGeometry

@Composable
fun ChatHistoryScreen(
    navController: NavController,
    viewModel: ChatHistoryViewModel
) {
    val chatHistory by viewModel.chatList.collectAsState()
    val geometry = LocalGeometry.current
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
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
                    modifier = Modifier.fillMaxWidth().clickable {
                        navController.navigate(Chat(chatRecord.id))
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.padding(geometry.paddingSmall),
                    thickness = Dp.Hairline,
                )
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate(NewChat) }
        ) {
            Text(text = "New Chat")
        }
    }
}
