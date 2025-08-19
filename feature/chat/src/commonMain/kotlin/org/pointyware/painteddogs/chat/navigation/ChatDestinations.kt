package org.pointyware.painteddogs.chat.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.chat.ui.ChatHistoryScreen
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryViewModel
import org.pointyware.painteddogs.core.navigation.Destination
import kotlin.uuid.ExperimentalUuidApi


/**
 * A list of a user's chat history.
 */
@Serializable
data object ChatHistory: Destination

/**
 * An individual chat session.
 * @param id The unique ID of the specific chat.
 */
@Serializable
data class Chat(val id: String): Destination


@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.chatRouting(
    navController: NavController
) {
    composable<ChatHistory> {
        val di = remember { getKoin() }

        val chatViewModel = remember { ChatHistoryViewModel() }
        ChatHistoryScreen(
            viewModel = chatViewModel
        )
    }
}
