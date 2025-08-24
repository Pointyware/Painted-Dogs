package org.pointyware.painteddogs.chat.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.chat.ChatHistoryScreen
import org.pointyware.painteddogs.chat.ChatScreen
import org.pointyware.painteddogs.chat.NewChatScreen
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryViewModel
import org.pointyware.painteddogs.chat.viewmodels.ChatViewModel
import org.pointyware.painteddogs.chat.viewmodels.NewChatViewModel
import org.pointyware.painteddogs.core.navigation.Destination
import kotlin.uuid.ExperimentalUuidApi

sealed interface ChatDestination: Destination {
    @Serializable
    data object History: ChatDestination
    @Serializable
    data object New: ChatDestination
    @Serializable
    data class Session(val id: String): ChatDestination
}
/**
 * A list of a user's chat history.
 */
@Deprecated("Poorly organized", ReplaceWith("ChatDestination.History"))
@Serializable
data object ChatHistory: Destination

/**
 * An individual chat session.
 * @param id The unique ID of the specific chat.
 */
@Serializable
@Deprecated("Poorly organized", ReplaceWith("ChatDestination.Session"))
data class Chat(val id: String): Destination


@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.chatRouting(
    navController: NavController
) {
    composable<ChatHistory> {
        val chatViewModel = remember { ChatHistoryViewModel() }
        ChatHistoryScreen(
            viewModel = chatViewModel,
            navController = navController
        )
    }

    composable<ChatDestination.New> {
        val koin = remember { getKoin() }
        val newChatViewModel = remember { NewChatViewModel(koin.get(), koin.get()) }

        NewChatScreen(
            newChatViewModel,
            navController
        )
    }

    composable<Chat> {
        val route = it.toRoute<Chat>()
        val messagesViewModel = remember { ChatViewModel() }

        LaunchedEffect(route.id) {
            messagesViewModel.loadMessages(route.id)
        }
        ChatScreen(
            viewModel = messagesViewModel,
            navController = navController
        )
    }
}
