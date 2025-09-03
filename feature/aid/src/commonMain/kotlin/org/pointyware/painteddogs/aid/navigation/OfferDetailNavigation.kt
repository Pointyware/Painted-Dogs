package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import kotlinx.serialization.Serializable


/**
 * Detail screen of a [org.pointyware.painteddogs.aid.entities.ResourceOffer]
 * indicated by the given [offerId].
 */
@Serializable
data class OfferDetailDestination(val offerId: String): AidDestination

/**
 * Navigates to the [OfferDetailDestination].
 */
fun NavController.navigateToOfferDetail(offerId: String) {
    navigate(OfferDetailDestination(offerId)) {
        popUpTo(graph.findStartDestination().id)
    }
}
