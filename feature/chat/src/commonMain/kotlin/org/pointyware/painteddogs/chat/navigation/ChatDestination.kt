package org.pointyware.painteddogs.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.core.navigation.Destination

@Serializable
sealed interface ChatDestination: Destination

/**
 *
 */
@Serializable
data object ChatRootDestination: ChatDestination

fun NavController.navigateToChatRoot() {
    navigate(ChatRootDestination) {
        launchSingleTop = true
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        restoreState = true
    }
}
