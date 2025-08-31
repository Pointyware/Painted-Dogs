package org.pointyware.painteddogs.aid.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
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

/**
 * Routing logic for Mutual Aid screens.
 */
fun NavGraphBuilder.aidRouting(
    onProvideRequest: (ResourceRequest)->Unit,
    onClaimOffer: (ResourceOffer)->Unit,
    onNavigateToRequest: (ResourceRequest)->Unit,
    onNavigateToOffer: (ResourceOffer)-> Unit,
) {
    composable<AidDestination.Board> {
        val viewModel: MutualAidViewModel = composeKoinViewModel()
        val state by viewModel.state.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.onProvideRequest.collect { request ->
                onProvideRequest(request)
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

    composable<AidDestination.Offer> {
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

    composable<AidDestination.Request> {
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
}
