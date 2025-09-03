package org.pointyware.painteddogs.aid.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.ui.OfferScreen
import org.pointyware.painteddogs.aid.ui.OfferScreenState
import org.pointyware.painteddogs.aid.viewmodels.OfferViewModel
import org.pointyware.painteddogs.core.ui.composeKoinViewModel


/**
 * The Screen where a user can offer a resource for the mutual aid network.
 */
@Serializable
data class DraftOfferDestination(
    val resource: Resource
): AidDestination

/**
 * Navigates to the [DraftOfferDestination].
 */
fun NavController.navigateToOfferDraft(resource: Resource) {
    navigate(DraftOfferDestination(resource)) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.findStartDestination().id)
    }
}

fun NavGraphBuilder.offerDraftDestination(
    onNavigateToOffer: (ResourceOffer)->Unit,
) {

    composable<DraftOfferDestination> { navEntry ->
        val route = navEntry.toRoute<DraftOfferDestination>()
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
}
