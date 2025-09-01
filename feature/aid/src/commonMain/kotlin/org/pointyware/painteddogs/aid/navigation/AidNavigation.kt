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

/**
 * Detail screen to define claim parameters in response to
 * a specific [org.pointyware.painteddogs.aid.entities.ResourceOffer]
 * indicated by the given [offerId].
 */
@Serializable
data class ClaimDetailDestination(val offerId: String): AidDestination
fun NavController.navigateToClaimDetail(offerId: String) {
    navigate(ClaimDetailDestination(offerId))
}

/**
 * Detail screen of a [org.pointyware.painteddogs.aid.entities.ResourceRequest]
 * indicated by the given [requestId].
 */
@Serializable
data class RequestDetailDestination(val requestId: String): AidDestination

fun NavController.navigateToRequestDetail(requestId: String) {
    navigate(RequestDetailDestination(requestId)) {
        popUpTo(graph.findStartDestination().id)
    }
}

/**
 * Detail screen of a [org.pointyware.painteddogs.aid.entities.ResourceOffer]
 * indicated by the given [offerId].
 */
@Serializable
data class OfferDetailDestination(val offerId: String): AidDestination

/**
 *
 */
fun NavController.navigateToOfferDetail(offerId: String) {
    navigate(OfferDetailDestination(offerId)) {
        popUpTo(graph.findStartDestination().id)
    }
}

/**
 * The Screen where a user can offer a resource for the mutual aid network.
 */
@Serializable
data class DraftOfferDestination(
    val resource: Resource
): AidDestination

/**
 *
 */
fun NavController.navigateToOfferDraft(resource: Resource) {
    navigate(DraftOfferDestination(resource)) {
        launchSingleTop = true
        restoreState = true
        popUpTo(route = AidDestination.Board)
    }
}
