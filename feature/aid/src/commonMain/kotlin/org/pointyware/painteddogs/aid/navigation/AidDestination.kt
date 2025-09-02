package org.pointyware.painteddogs.aid.navigation

import kotlinx.serialization.Serializable
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

}

/**
 * The navigation type for the root of the mutual aid nested navigation graph.
 */
@Serializable
data object AidRootDestination: AidDestination
