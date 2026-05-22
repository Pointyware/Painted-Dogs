package org.pointyware.painteddogs.aid.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.aid.ui.OfferInfoScreen
import org.pointyware.painteddogs.aid.viewmodels.OfferInfoViewModel
import org.pointyware.painteddogs.core.ui.composeKoinViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


/**
 * Detail screen of a [org.pointyware.painteddogs.aid.entities.ResourceOffer]
 * indicated by the given [offerId].
 */
@Serializable
data class OfferDetailDestination(val offerId: String): AidDestination

/**
 * Navigates to the [OfferDetailDestination].
 */
fun NavController.navigateToOfferDetail(offerId: String) {
    navigate(OfferDetailDestination(offerId)) {
        popUpTo(graph.findStartDestination().id)
    }
}
@OptIn(ExperimentalUuidApi::class)
fun NavController.navigateToOfferDetail(offerUuid: Uuid) {
    navigateToOfferDetail(offerUuid.toString())
}
@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.offerDetailDestination() {
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
        val uuid = Uuid.parse(idString)
        val viewModel: OfferInfoViewModel = composeKoinViewModel()
        LaunchedEffect(idString) {
            viewModel.onInit(uuid)
        }
        val state by viewModel.state.collectAsState()
        val error by viewModel.error.collectAsState()
        OfferInfoScreen(
            state = state,
            error = error,
            onClearError = viewModel::onClearError
        )
    }
}
