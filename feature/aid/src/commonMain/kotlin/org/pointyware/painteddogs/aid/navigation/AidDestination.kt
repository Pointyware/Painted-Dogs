package org.pointyware.painteddogs.aid.navigation

import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.core.navigation.Destination

/**
 * Mutual Aid navigation destinations.
 */
@Serializable
open class AidDestination(): Destination {
    /**
     * The Mutual Aid notice board Screen with all requests and offers.
     */
    @Serializable
    data object Board: AidDestination()

    /**
     * The Screen where a user can offer a resource for the mutual aid network.
     */
    @Serializable
    data class Offer(
        val resource: Resource
    ): AidDestination()

    /**
     * The Screen where a user can request an offer for the mutual aid network.
     */
    @Serializable
    data class Request(
        val resource: Resource
    ): AidDestination()

    @Serializable
    data class ClaimDetail(val offerId: String): AidDestination()
    @Serializable
    data class ProviderDetail(val requestId: String): AidDestination()
    @Serializable
    data class RequestDetail(val requestId: String): AidDestination()
    @Serializable
    data class OfferDetail(val offerId: String): AidDestination()
}
