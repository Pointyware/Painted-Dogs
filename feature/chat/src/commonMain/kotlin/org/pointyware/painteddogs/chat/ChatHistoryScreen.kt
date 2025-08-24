package org.pointyware.painteddogs.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.chat.navigation.ChatDestination
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
                                label = participant.username,
                                image = ParticipantImage.NoProfileImage
                            )
                        },
                        excerpt = chatRecord.excerpt,
                    ),
                    modifier = Modifier.fillMaxWidth().clickable {
                        navController.navigate(ChatDestination.Session(chatRecord.id))
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.padding(geometry.paddingSmall),
                    thickness = Dp.Hairline,
                )
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(LocalGeometry.current.paddingSmall)
                .safeContentPadding(),
            onClick = {
                navController.navigate(ChatDestination.New) {
                    popUpTo(ChatDestination.History)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = stringResource(Res.string.acc_new_chat)
            )
        }
    }
}
