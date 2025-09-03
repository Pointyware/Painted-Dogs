package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.aid.entities.Resource


/**
 * The Screen where a user can offer a resource for the mutual aid network.
 */
@Serializable
data class DraftOfferDestination(
    val resource: Resource
): AidDestination

/**
 * Navigates to the [DraftOfferDestination].
 */
fun NavController.navigateToOfferDraft(resource: Resource) {
    navigate(DraftOfferDestination(resource)) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.findStartDestination().id)
    }
}
