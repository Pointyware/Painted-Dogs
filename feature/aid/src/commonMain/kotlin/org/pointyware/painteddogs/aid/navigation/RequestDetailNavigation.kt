package org.pointyware.painteddogs.aid.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


/**
 * Detail screen of a [org.pointyware.painteddogs.aid.entities.ResourceRequest]
 * indicated by the given [requestUuid].
 */
@Serializable
data class RequestDetailDestination(val requestUuid: String): AidDestination {
    @OptIn(ExperimentalUuidApi::class)
    val uuid: Uuid get() = Uuid.parse(requestUuid)
}

/**
 * Navigates to the [RequestDetailDestination].
 */
fun NavController.navigateToRequestDetail(requestUuid: String) {
    navigate(RequestDetailDestination(requestUuid)) {
        popUpTo(graph.findStartDestination().id)
    }
}
@OptIn(ExperimentalUuidApi::class)
fun NavController.navigateToRequestDetail(requestUuid: Uuid) {
    navigateToRequestDetail(requestUuid.toString())
}

fun NavGraphBuilder.requestDetailDestination() {
    composable<RequestDetailDestination> {
        val route = it.toRoute<RequestDetailDestination>()
        Text(text = "Route: $route")
    }
}
