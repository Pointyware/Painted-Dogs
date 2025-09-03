package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavGraphBuilder
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import kotlin.uuid.ExperimentalUuidApi

/**
 * Routing logic for Mutual Aid screens.
 */
@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.aidRouting(
    onSupportRequest: (ResourceRequest)->Unit,
    onClaimOffer: (ResourceOffer)->Unit,
    onNavigateToRequest: (ResourceRequest)->Unit,
    onNavigateToOffer: (ResourceOffer)-> Unit,
    onCreateOffer: (Resource)->Unit,
) {
    exchangeBoardDestination(
        onSupportRequest = onSupportRequest,
        onClaimOffer = onClaimOffer
    )

    offerDraftDestination(
        onNavigateToOffer = onNavigateToOffer
    )

    requestDraftDestination(
        onNavigateToRequest = onNavigateToRequest
    )

    supportDetailDestination()
    claimDetailDestination()
    requestDetailDestination()
    offerDetailDestination()
}
