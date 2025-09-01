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
    onCreateOffer: (Resource)->Unit,
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
                    resources = it.resourceFilter,
                    category = it.category
                )
            },
            onOfferClaim = viewModel::onOfferClaim,
            onRequestResponse = viewModel::onRequestResponse,
            onResourceFilterChanged = viewModel::onResourceFilterChanged,
            onResourceCategoryChanged = viewModel::onSetResourceCategory,
            onCreateOffer = onCreateOffer
        )
    }

    composable<AidDestination.DraftOffer> { navEntry ->
        val route = navEntry.toRoute<AidDestination.DraftOffer>()
        val viewModel: OfferViewModel = composeKoinViewModel()
        val state by viewModel.state.collectAsState()
        val error by viewModel.error.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.onSetResourceCategory(route.resource)
            viewModel.onOfferCreated.collect {
                onNavigateToOffer(it)
            }
        }
        OfferScreen(
            state = state.let {
                OfferScreenState(
                    description = it.description,
                    category = it.category,
                    scope = it.scope,
                )
            },
            onSelectTemporalScope = viewModel::onSelectTemporalScope,
            onChangeDescription = viewModel::onChangeDescription,
            onSelectResourceCategory = viewModel::onSetResourceCategory,
            onSubmit = viewModel::onSubmit,
            throwable = error,
            onClearError = viewModel::onClearError,
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
    composable<AidDestination.RequestDetail>(
        deepLinks = listOf(
            navDeepLink<AidDestination.RequestDetail>(
                basePath = "pdogs://"
            ) {
                uriPattern = "pdogs://request/{requestId}"
                action = "android.intent.action.VIEW"
            }
        ),
    ) {
        val idString = it.toRoute<AidDestination.RequestDetail>().requestId
        val hex = Uuid.parse(idString)
        Text(text = "Request: ${hex.toHexString()}")
    }
    composable<AidDestination.OfferDetail>(
        deepLinks = listOf(
            navDeepLink<AidDestination.OfferDetail>(
                basePath = "pdogs://"
            ) {
                uriPattern = "pdogs://offer/{offerId}"
                action = "android.intent.action.VIEW"
            }
        ),
    )  {
        val idString = it.toRoute<AidDestination.OfferDetail>().offerId
        val hex = Uuid.parse(idString)
        Text(text = "Offer: ${hex.toHexString()}")
    }
}
