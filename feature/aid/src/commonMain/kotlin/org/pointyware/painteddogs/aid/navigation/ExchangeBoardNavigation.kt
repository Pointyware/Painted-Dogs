package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavController
import kotlinx.serialization.Serializable


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
