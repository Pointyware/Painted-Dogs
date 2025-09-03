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
