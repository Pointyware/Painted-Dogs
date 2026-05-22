package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavGraphBuilder
import org.pointyware.painteddogs.aid.entities.Resource
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Routing logic for Mutual Aid screens.
 *
 * @param navigateToRequestDetail Navigate to a detail screen where a user can
 * confirm request details.
 * @param onClaimOffer Navigate to claim confirmation screen.
 * @param onNavigateToRequest Navigate to specific resource request info screen.
 * @param onNavigateToOffer Navigate to specific resource offer info screen.
 * @param onCreateOffer Navigate to a detail screen where a user can confirm offer details.
 */
@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.aidRouting(
    navigateToRequestDetail: (Uuid)->Unit,
    onClaimOffer: (Uuid)->Unit,
    onNavigateToRequest: (Uuid)->Unit,
    onNavigateToOffer: (Uuid)-> Unit,
    onCreateOffer: (Resource)->Unit,
) {
    exchangeBoardDestination(
        onSupportRequest = navigateToRequestDetail,
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
