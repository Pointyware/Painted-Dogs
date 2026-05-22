package org.pointyware.painteddogs.chat.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.chat.ChatHistoryScreen
import org.pointyware.painteddogs.chat.ChatHistoryScreenState
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryViewModel

/**
 * Screen listing a user's chat history.
 */
@Serializable
data object ChatHistoryDestination: ChatDestination

fun NavController.navigateToChatHistory() {
    navigate(ChatHistoryDestination) {
        launchSingleTop
        popUpTo(ChatHistoryDestination) {
            saveState = true
        }
        restoreState = true
    }
}

fun NavGraphBuilder.chatHistoryDestination(
    onNavigateToChatSession: (String)->Unit,
    onNavigateToNewChat: ()->Unit,
) {
    composable<ChatHistoryDestination> {
        val koin = remember { getKoin() }
        val chatViewModel = remember { ChatHistoryViewModel(koin.get()) }
        val state by chatViewModel.chatList.collectAsState()

        LaunchedEffect(Unit) {
            chatViewModel.onInit()
        }
        ChatHistoryScreen(
            state = ChatHistoryScreenState(
                chats = state
            ),
            onViewChatSession = { chatId ->
                onNavigateToChatSession(chatId)
            },
            onCreateNewChat = onNavigateToNewChat
        )
    }
}
