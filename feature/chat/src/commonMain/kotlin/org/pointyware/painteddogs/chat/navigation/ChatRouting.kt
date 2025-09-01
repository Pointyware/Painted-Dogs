package org.pointyware.painteddogs.chat.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.chat.ChatHistoryScreen
import org.pointyware.painteddogs.chat.ChatScreen
import org.pointyware.painteddogs.chat.NewChatScreen
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryViewModel
import org.pointyware.painteddogs.chat.viewmodels.ChatViewModel
import org.pointyware.painteddogs.chat.viewmodels.NewChatViewModel
import kotlin.uuid.ExperimentalUuidApi

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
            onViewChatSession = { chatId ->
                navController.navigate(ChatDestination.Session(chatId))
            },
            onCreateNewChat = {
                navController.navigate(ChatDestination.New) {
                    launchSingleTop = true
                }
            }
        )
    }

    composable<ChatDestination.New> {
        val koin = remember { getKoin() }
        val newChatViewModel = remember { NewChatViewModel(koin.get(), koin.get(), koin.get()) }

        NewChatScreen(
            newChatViewModel,
            navigateToChatDetails = { chatId ->
                navController.navigate(ChatDestination.Session(chatId))
            }
        )
    }

    composable<ChatDestination.Session> {
        val route = it.toRoute<ChatDestination.Session>()
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
