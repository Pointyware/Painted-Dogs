package org.pointyware.painteddogs.aid.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.ui.ExchangeBoardScreen
import org.pointyware.painteddogs.aid.ui.ExchangeBoardScreenState
import org.pointyware.painteddogs.aid.viewmodels.MutualAidViewModel
import org.pointyware.painteddogs.core.ui.composeKoinViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


/**
 * The Mutual Aid notice board Screen with all requests and offers.
 */
@Serializable
data object ExchangeBoardDestination: AidDestination

/**
 * Navigate to the main [ExchangeBoardDestination] of the mutual aid tab.
 */
fun NavController.navigateToExchangeBoard() {
    navigate(ExchangeBoardDestination) {
        popUpTo(ExchangeBoardDestination) {
            saveState = true
            inclusive = true
        }
        restoreState = true
    }
}

@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.exchangeBoardDestination(
    onSupportRequest: (Uuid)->Unit,
    onClaimOffer: (Uuid)->Unit,
    onCreateOffer: (Resource)->Unit
) {
    composable<ExchangeBoardDestination> {
        val viewModel: MutualAidViewModel = composeKoinViewModel()
        val state by viewModel.state.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.onProvideRequest.collect { request ->
                onSupportRequest(request.id)
            }
            viewModel.onClaimOffer.collect { offer ->
                onClaimOffer(offer.id)
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
}
