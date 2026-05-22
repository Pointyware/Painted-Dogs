package org.pointyware.painteddogs.chat.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.chat.NewChatScreen
import org.pointyware.painteddogs.chat.viewmodels.NewChatViewModel


/**
 * Screen for creating a new chat session.
 */
@Serializable
data object NewChatDestination: ChatDestination

/**
 * Navigates to a New Chat Screen to create a new chat session.
 */
fun NavController.navigateToNewChat() {
    navigate(NewChatDestination) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.newChatDestination(
    onNavigateToChatSession: (String)->Unit,
) {

    composable<NewChatDestination> {
        val koin = remember { getKoin() }
        val newChatViewModel = remember { NewChatViewModel(koin.get(), koin.get(), koin.get()) }

        NewChatScreen(
            newChatViewModel,
            onNavigateToChatSession = onNavigateToChatSession
        )
    }
}
