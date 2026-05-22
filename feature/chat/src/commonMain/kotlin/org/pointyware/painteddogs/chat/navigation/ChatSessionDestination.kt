package org.pointyware.painteddogs.chat.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.chat.ChatScreen
import org.pointyware.painteddogs.chat.viewmodels.ChatViewModel


/**
 * Screen for an individual chat session.
 * @param id The unique ID of the specific chat.
 */
@Serializable
data class ChatSessionDestination(val id: String): ChatDestination

/**
 * Navigates to an existing chat session indicated by the [chatId].
 */
fun NavController.navigateToChatSession(chatId: String) {
    navigate(ChatSessionDestination(chatId))
}

fun NavGraphBuilder.chatSessionDestination(

) {
    composable<ChatSessionDestination> {
        val route = it.toRoute<ChatSessionDestination>()
        val koin = remember { getKoin() }
        val messagesViewModel = remember { ChatViewModel(koin.get()) }

        LaunchedEffect(route.id) {
            messagesViewModel.loadMessages(route.id)
        }
        ChatScreen(
            viewModel = messagesViewModel,
        )
    }
}
