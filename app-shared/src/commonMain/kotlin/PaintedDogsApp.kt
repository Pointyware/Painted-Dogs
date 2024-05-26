package org.pointyware.painteddogs.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.navigation.StackNavigationControllerImpl
import org.pointyware.painteddogs.core.navigation.LocationRoot
import org.pointyware.painteddogs.core.ui.CollectionInfoScreen
import org.pointyware.painteddogs.core.ui.CollectionHistoryScreen
import org.pointyware.painteddogs.core.ui.ContributionInfoScreen
import org.pointyware.painteddogs.core.ui.ContributionDetailsScreen
import org.pointyware.painteddogs.core.ui.FundDetailsScreen
import org.pointyware.painteddogs.core.ui.FundHistoryScreen
import org.pointyware.painteddogs.core.ui.FundDetailsScreenState
import org.pointyware.painteddogs.core.ui.FundHistoryScreenState
import org.pointyware.painteddogs.core.ui.HomeScreen
import org.pointyware.painteddogs.core.ui.HomeScreenState
import org.pointyware.painteddogs.core.ui.ProfileScreen
import org.pointyware.painteddogs.core.ui.ProfileScreenState
import org.pointyware.painteddogs.core.ui.SearchCollectionsScreen
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme

/**
 * The main entry point for the Painted Dogs app.
 */
@Composable
fun PaintedDogsApp(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    val navController = StackNavigationControllerImpl<String?, Any>(null)
    // val androidNavController = NavigationController<String(Url), Bundle>()

    PaintedDogsTheme(
        isDark = isDarkTheme
    ) {
        LocationRoot( // TODO: rename to ScreenHost or something more meaningful
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
            // a user needs to control how they appear to others
            location("users/\$id/profile") {
                ProfileScreen(
                    state = ProfileScreenState(
                        userId = "123",
                        username = "johndoe",
                    ),
                    onEditProfile = {},
                    onViewCollections = { navController.navigateTo("users/123/collections") },
                    onViewContributions = { navController.navigateTo("users/123/contributions") },
                    onLogout = {},
                )
            }

            // we need to make a collection before anyone can contribute
            location("collections/create") {
                FundDetailsScreen(
                    state = FundDetailsScreenState(
                        title = "My Collection",
                        description = "A collection of things",
                    ),
                    onConfirm = { navController.navigateTo("collections/123/contribute") },
                )
            }
            // a user can see all their current and past collections
            location("users/\$id/collections") {
                CollectionHistoryScreen()
            }
            // a user needs to find a collection to contribute to
            location("collections/search") {
                SearchCollectionsScreen()
            }
            // a user needs to see the details of a collection before deciding to contribute
            location("collections/\$collectionId") {
                CollectionInfoScreen()
            }
            // a user needs to determine how much they want to contribute
            location("collections/\$collectionId/contribute") {
                ContributionDetailsScreen()
            }
            // we need to show the user the details of their contribution after server confirmation
            location("collections/\$collectionId/contributions/\$contributionId") {
                ContributionInfoScreen()
            }
            // a user needs to control how they appear to others
            location("users/\$id/contributions") {
                FundHistoryScreen(
                    state = FundHistoryScreenState(
                        contributions = emptyList(),
                    ),
                    onViewFund = { fundId -> navController.navigateTo("collections/$fundId") },
                )
            }
        }
    }
}
