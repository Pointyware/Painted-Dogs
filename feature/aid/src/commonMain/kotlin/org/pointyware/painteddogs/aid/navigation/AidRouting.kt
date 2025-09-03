package org.pointyware.painteddogs.aid.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.ui.RequestScreen
import org.pointyware.painteddogs.aid.ui.RequestScreenState
import org.pointyware.painteddogs.aid.viewmodels.RequestViewModel
import org.pointyware.painteddogs.core.ui.composeKoinViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

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

    composable<DraftRequestDestination> {
        val viewModel: RequestViewModel = composeKoinViewModel()
        val state by viewModel.state.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.onRequestCreated.collect {
                onNavigateToRequest(it)
            }
        }
        RequestScreen(
            state = state.let {
                RequestScreenState(
                    temporalScope = it.temporalScope,
                    description = it.description,
                )
            },
            onScopeSelected = viewModel::onTemporalScopeSelected,
            onSubmit = viewModel::onSubmit,
        )
    }

    composable<SupportDetailDestination> {
        val idString = it.toRoute<SupportDetailDestination>().requestId
        val hex = Uuid.parse(idString)
        Text(text = "Support Detail: ${hex.toHexString()}")
    }
    composable<ClaimDetailDestination> {
        val idString = it.toRoute<ClaimDetailDestination>().offerId
        val hex = Uuid.parse(idString)
        Text(text = "Claim Detail: ${hex.toHexString()}")
    }
    composable<RequestDetailDestination>(
        deepLinks = listOf(
            navDeepLink<RequestDetailDestination>(
                basePath = "pdogs://"
            ) {
                uriPattern = "pdogs://request/{requestId}"
                action = "android.intent.action.VIEW"
            }
        ),
    ) {
        val idString = it.toRoute<RequestDetailDestination>().requestId
        val hex = Uuid.parse(idString)
        Text(text = "Request: ${hex.toHexString()}")
    }
    composable<OfferDetailDestination>(
        deepLinks = listOf(
            navDeepLink<OfferDetailDestination>(
                basePath = "pdogs://"
            ) {
                uriPattern = "pdogs://offer/{offerId}"
                action = "android.intent.action.VIEW"
            }
        ),
    )  {
        val idString = it.toRoute<OfferDetailDestination>().offerId
        val hex = Uuid.parse(idString)
        Text(text = "Offer: ${hex.toHexString()}")
    }
}
