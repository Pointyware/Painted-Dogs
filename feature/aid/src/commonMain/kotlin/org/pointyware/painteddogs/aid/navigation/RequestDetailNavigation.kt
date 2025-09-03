package org.pointyware.painteddogs.aid.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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

fun NavGraphBuilder.requestDetailDestination() {
    composable<RequestDetailDestination> {
        val route = it.toRoute<RequestDetailDestination>()
        Text(text = "Route: $route")
    }
}
