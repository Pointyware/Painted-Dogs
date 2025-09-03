package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.aid.entities.Resource


/**
 * The Screen where a user can request an offer for the mutual aid network.
 */
@Serializable
data class DraftRequestDestination(
    val resource: Resource
): AidDestination

/**
 *
 */
fun NavController.navigateToRequestDraft(resource: Resource) {
    navigate(DraftRequestDestination(resource)) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.findStartDestination().id)
    }
}
