package org.pointyware.painteddogs.aid.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.ui.ExchangeBoardScreen
import org.pointyware.painteddogs.aid.ui.ExchangeBoardScreenState
import org.pointyware.painteddogs.aid.ui.OfferScreen
import org.pointyware.painteddogs.aid.ui.OfferScreenState
import org.pointyware.painteddogs.aid.ui.RequestScreen
import org.pointyware.painteddogs.aid.ui.RequestScreenState
import org.pointyware.painteddogs.aid.viewmodels.MutualAidViewModel
import org.pointyware.painteddogs.aid.viewmodels.OfferViewModel
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
) {
    composable<AidDestination.Board> {
        val viewModel: MutualAidViewModel = composeKoinViewModel()
        val state by viewModel.state.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.onProvideRequest.collect { request ->
                onSupportRequest(request)
            }
            viewModel.onClaimOffer.collect { offer ->
                onClaimOffer(offer)
            }
        }
        ExchangeBoardScreen(
            state = state.let {
                ExchangeBoardScreenState(
                    posts = it.posts,
                    resources = it.resourceFilter
                )
            },
            onOfferClaim = viewModel::onOfferClaim,
            onRequestResponse = viewModel::onRequestResponse,
            onResourceFilterChanged = viewModel::onResourceFilterChanged,
        )
    }

    composable<AidDestination.DraftOffer> {
        val viewModel: OfferViewModel = composeKoinViewModel()
        val state by viewModel.state.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.onOfferCreated.collect {
                onNavigateToOffer(it)
            }
        }
        OfferScreen(
            state = state.let {
                OfferScreenState(
                    title = it.title,
                    scope = it.scope,
                )
            },
            onSelectTemporalScope = viewModel::onSelectTemporalScope,
            onChangeTitle = viewModel::onChangeTitle,
            onSubmit = viewModel::onSubmit,
        )
    }

    composable<AidDestination.DraftRequest> {
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

    composable<AidDestination.SupportDetail> {
        val idString = it.toRoute<AidDestination.SupportDetail>().requestId
        val hex = Uuid.parse(idString)
        Text(text = "Support Detail: ${hex.toHexString()}")
    }
    composable<AidDestination.ClaimDetail> {
        val idString = it.toRoute<AidDestination.ClaimDetail>().offerId
        val hex = Uuid.parse(idString)
        Text(text = "Claim Detail: ${hex.toHexString()}")
    }
    composable<AidDestination.RequestDetail> {
        val idString = it.toRoute<AidDestination.RequestDetail>().requestId
        val hex = Uuid.parse(idString)
        Text(text = "Request: ${hex.toHexString()}")
    }
    composable<AidDestination.OfferDetail> {
        val idString = it.toRoute<AidDestination.OfferDetail>().offerId
        val hex = Uuid.parse(idString)
        Text(text = "Offer: ${hex.toHexString()}")
    }
}
