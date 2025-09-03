package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import kotlinx.serialization.Serializable


/**
 * Detail screen of a [org.pointyware.painteddogs.aid.entities.ResourceRequest]
 * indicated by the given [requestId].
 */
@Serializable
data class RequestDetailDestination(val requestId: String): AidDestination

/**
 * Navigates to the [RequestDetailDestination].
 */
fun NavController.navigateToRequestDetail(requestId: String) {
    navigate(RequestDetailDestination(requestId)) {
        popUpTo(graph.findStartDestination().id)
    }
}
