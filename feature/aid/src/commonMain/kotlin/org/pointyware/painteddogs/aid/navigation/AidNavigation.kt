package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.aid.entities.Resource


/**
 * Detail screen to define support parameters in response to
 * a specific [org.pointyware.painteddogs.aid.entities.ResourceRequest]
 * indicated by the given [requestId].
 */
@Serializable
data class SupportDetailDestination(val requestId: String): AidDestination
fun NavController.navigateToSupportDetail(requestId: String) {
    navigate(SupportDetailDestination(requestId))
}

fun NavController.navigateToClaimDetail(offerId: String) {
    navigate(ClaimDetail(offerId))
}

fun NavController.navigateToRequestDetail(requestId: String) {
    navigate(AidDestination.RequestDetail(requestId)) {
        popUpTo(graph.findStartDestination().id)
    }
}

fun NavController.navigateToOfferDetail(offerId: String) {
    navigate(AidDestination.OfferDetail(offerId)) {
        popUpTo(graph.findStartDestination().id)
    }
}

fun NavController.navigateToOfferDraft(resource: Resource) {
    navigate(AidDestination.DraftOffer(resource)) {
        launchSingleTop = true
        restoreState = true
        popUpTo(route = AidDestination.Board)
    }
}
