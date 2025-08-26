package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.pointyware.painteddogs.aid.MutualAidScreen
import org.pointyware.painteddogs.aid.OfferScreen
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

    }
}
