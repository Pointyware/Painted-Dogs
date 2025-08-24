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
    /**
     * Screen listing a user's chat history.
     */
    @Serializable
    data object History: ChatDestination

    /**
     * Screen for creating a new chat session.
     */
    @Serializable
    data object New: ChatDestination

    /**
     * Screen for an individual chat session.
     * @param id The unique ID of the specific chat.
     */
    @Serializable
    data class Session(val id: String): ChatDestination
}

@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.chatRouting(
    navController: NavController
) {
    composable<ChatDestination.History> {
        val koin = remember { getKoin() }
        val chatViewModel = remember { ChatHistoryViewModel(koin.get()) }

        LaunchedEffect(Unit) {
            chatViewModel.onInit()
        }
        ChatHistoryScreen(
            viewModel = chatViewModel,
            navController = navController
        )
    }

    composable<ChatDestination.New> {
        val koin = remember { getKoin() }
        val newChatViewModel = remember { NewChatViewModel(koin.get(), koin.get(), koin.get()) }

        NewChatScreen(
            newChatViewModel,
            navController
        )
    }

    composable<ChatDestination.Session> {
        val route = it.toRoute<ChatDestination.Session>()
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
