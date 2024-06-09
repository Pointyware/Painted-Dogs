package org.pointyware.painteddogs.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.app.di.AppDependencies
import org.pointyware.painteddogs.app.screens.HomeScreen
import org.pointyware.painteddogs.app.screens.HomeScreenState
import org.pointyware.painteddogs.core.navigation.LocationRoot
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import org.pointyware.painteddogs.feature.collections.core.navigation.fundsNavigation
import org.pointyware.painteddogs.feature.profiles.profileNavigation

/**
 * The main entry point for the Painted Dogs app.
 */
@Composable
fun PaintedDogsApp(
    dependencies: AppDependencies,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    val navController = dependencies.getNavigationDependencies().getNavController()

    PaintedDogsTheme(
        isDark = isDarkTheme
    ) {
        LocationRoot(
            navController = navController,
            modifier = modifier,
        ) {
            // user home screen; entry point
            location(null) {
                HomeScreen(
                    state = HomeScreenState(),
                    onCreateFund = { navController.navigateTo("collections/create") },
                    onSearchFunds = { navController.navigateTo("collections/search") },
                    onViewProfile = { navController.navigateTo("users/123/profile") },
                    onFundSelected = { collectionId -> navController.navigateTo("collections/$collectionId") },
                )
            }

            profileNavigation(
                navigationDependencies = dependencies.getNavigationDependencies(),
                profileDependencies = dependencies.getProfileDependencies(),
            )

            fundsNavigation(
                navigationDependencies = dependencies.getNavigationDependencies(),
                fundDependencies = dependencies.getFundDependencies(),
            )
            // fundsNavigation: donations/funds
            // ridesNavigation: ride shares
            // businessNavigation: labor/business/reporting/negotiation/
            // actionNavigation: strikes/petitions/etc.
            // eventsNavigation: strikes?
            // socialNavigation: petitions?
        }
    }
}
