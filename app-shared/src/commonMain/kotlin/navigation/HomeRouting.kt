package org.pointyware.painteddogs.shared.navigation

import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.core.navigation.StackNavigationController
import org.pointyware.painteddogs.shared.screens.HomeScreen
import org.pointyware.painteddogs.shared.screens.HomeScreenState

/**
 * TODO: describe purpose/intent of HomeRouting
 */
fun LocationRootScope<String?>.homeRouting(navController: StackNavigationController<String?, Any>) {
    location(null) {
        HomeScreen(
            state = HomeScreenState(),
            onCreateFund = { navController.navigateTo("funds/create") },
            onSearchFunds = { navController.navigateTo("funds/search") },
            onViewProfile = { navController.navigateTo("users/123/profile") },
            onFundSelected = { collectionId -> navController.navigateTo("funds/$collectionId") },
        )
    }
}
