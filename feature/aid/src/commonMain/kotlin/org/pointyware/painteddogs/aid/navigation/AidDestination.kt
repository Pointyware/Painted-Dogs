package org.pointyware.painteddogs.aid.navigation

import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.core.navigation.Destination

/**
 * Mutual Aid navigation destinations.
 *
 * Board --> DraftRequest
 * Board --> DraftOffer
 * Board --> SupportDetail
 * Board --> ClaimDetail
 *
 * DraftRequest --> RequestDetail
 * DraftOffer --> OfferDetail
 */
@Serializable
sealed interface AidDestination: Destination {
    /**
     * The Mutual Aid notice board Screen with all requests and offers.
     */
    @Serializable
    data object Board: AidDestination

    /**
     * The Screen where a user can offer a resource for the mutual aid network.
     */
    @Serializable
    data class DraftOffer(
        val resource: Resource
    ): AidDestination

    /**
     * The Screen where a user can request an offer for the mutual aid network.
     */
    @Serializable
    data class DraftRequest(
        val resource: Resource
    ): AidDestination

    /**
     * Detail screen of a [org.pointyware.painteddogs.aid.entities.ResourceOffer]
     * indicated by the given [offerId].
     */
    @Serializable
    data class OfferDetail(val offerId: String): AidDestination
}

/**
 * The navigation type for the root of the mutual aid nested navigation graph.
 */
@Serializable
data object AidRootDestination: AidDestination
