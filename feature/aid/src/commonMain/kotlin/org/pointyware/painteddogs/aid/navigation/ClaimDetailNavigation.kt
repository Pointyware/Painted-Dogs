package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavController
import kotlinx.serialization.Serializable


/**
 * Detail screen to define claim parameters in response to
 * a specific [org.pointyware.painteddogs.aid.entities.ResourceOffer]
 * indicated by the given [offerId].
 */
@Serializable
data class ClaimDetailDestination(val offerId: String): AidDestination

/**
 * Navigates to the [ClaimDetailDestination].
 */
fun NavController.navigateToClaimDetail(offerId: String) {
    navigate(ClaimDetailDestination(offerId))
}
