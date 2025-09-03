package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.core.navigation.Destination

/**
 * Mutual Aid navigation destinations.
 */
@Serializable
sealed interface AidDestination: Destination

/**
 * The navigation type for the root of the mutual aid nested navigation graph.
 */
@Serializable
data object AidRootDestination: AidDestination

fun NavController.navigateToAidRoot() {
    navigate(AidRootDestination) {
        launchSingleTop = true
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        restoreState = true
    }
}
