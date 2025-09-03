package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavGraphBuilder
import org.pointyware.painteddogs.aid.entities.Resource
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Routing logic for Mutual Aid screens.
 */
@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.aidRouting(
    onSupportRequest: (Uuid)->Unit,
    onClaimOffer: (Uuid)->Unit,
    onNavigateToRequest: (Uuid)->Unit,
    onNavigateToOffer: (Uuid)-> Unit,
    onCreateOffer: (Resource)->Unit,
) {
    exchangeBoardDestination(
        onSupportRequest = onSupportRequest,
        onClaimOffer = onClaimOffer,
        onCreateOffer = onCreateOffer
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
