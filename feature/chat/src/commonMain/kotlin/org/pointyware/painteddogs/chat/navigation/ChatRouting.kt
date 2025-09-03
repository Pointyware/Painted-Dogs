package org.pointyware.painteddogs.chat.navigation

import androidx.navigation.NavGraphBuilder
import kotlin.uuid.ExperimentalUuidApi


@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.chatRouting(
    onNavigateToNewChat: () -> Unit,
    onNavigateToChatSession: (String) -> Unit,
) {
    chatHistoryDestination(
        onNavigateToChatSession = onNavigateToChatSession,
        onNavigateToNewChat = onNavigateToNewChat
    )

    newChatDestination(
        onNavigateToChatSession = onNavigateToChatSession
    )

    chatSessionDestination(

    )
}
