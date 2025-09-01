package org.pointyware.painteddogs.chat.navigation

import androidx.navigation.NavController

/**
 * Navigates to a New Chat Screen to create a new chat session.
 */
fun NavController.navigateToNewChat() {
    navigate(ChatDestination.New) {
        launchSingleTop = true
    }
}

/**
 * Navigates to an existing chat session indicated by the [chatId].
 */
fun NavController.navigateToChatSession(chatId: String) {
    navigate(ChatDestination.Session(chatId))
}
