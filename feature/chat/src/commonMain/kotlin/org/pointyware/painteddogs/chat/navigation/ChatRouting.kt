package org.pointyware.painteddogs.chat.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.chat.ChatHistoryScreen
import org.pointyware.painteddogs.chat.ChatHistoryScreenState
import org.pointyware.painteddogs.chat.NewChatScreen
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryViewModel
import org.pointyware.painteddogs.chat.viewmodels.NewChatViewModel
import kotlin.uuid.ExperimentalUuidApi


fun NavGraphBuilder.chatHistoryDestination(
    onNavigateToChatSession: (String)->Unit,
    onNavigateToNewChat: ()->Unit,
) {
    composable<ChatDestination.History> {
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

fun NavGraphBuilder.newChatDestination(
    onNavigateToChatSession: (String)->Unit,
) {

    composable<ChatDestination.New> {
        val koin = remember { getKoin() }
        val newChatViewModel = remember { NewChatViewModel(koin.get(), koin.get(), koin.get()) }

        NewChatScreen(
            newChatViewModel,
            onNavigateToChatSession = onNavigateToChatSession
        )
    }
}

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
