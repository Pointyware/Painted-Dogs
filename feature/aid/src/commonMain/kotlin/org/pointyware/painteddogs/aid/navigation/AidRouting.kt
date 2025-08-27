package org.pointyware.painteddogs.aid.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.pointyware.painteddogs.aid.MutualAidScreen
import org.pointyware.painteddogs.aid.OfferScreen
import org.pointyware.painteddogs.aid.ui.RequestScreen
import org.pointyware.painteddogs.aid.ui.RequestScreenState
import org.pointyware.painteddogs.aid.viewmodels.RequestViewModel
import org.pointyware.painteddogs.core.ui.composeKoinViewModel

/**
 * Routing logic for Mutual Aid screens.
 */
fun NavGraphBuilder.aidRouting(
    navController: NavController
) {
    composable<AidDestination.Board> {
        MutualAidScreen(
            composeKoinViewModel(),
            navController
        )
    }

    composable<AidDestination.Offer> {
        OfferScreen(
            composeKoinViewModel(),
            navController
        )
    }

    composable<AidDestination.Request> {
        val viewModel: RequestViewModel = composeKoinViewModel()
        val state by viewModel.state.collectAsState()
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
