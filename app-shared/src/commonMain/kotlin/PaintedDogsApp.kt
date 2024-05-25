package org.pointyware.painteddogs.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.navigation.StackNavigationControllerImpl
import org.pointyware.painteddogs.core.navigation.compose.LocationRoot
import org.pointyware.painteddogs.core.ui.CollectionInfoScreen
import org.pointyware.painteddogs.core.ui.CollectionHistoryScreen
import org.pointyware.painteddogs.core.ui.ContributionInfoScreen
import org.pointyware.painteddogs.core.ui.ContributionDetailsScreen
import org.pointyware.painteddogs.core.ui.CollectionDetailsScreen
import org.pointyware.painteddogs.core.ui.ContributionHistoryScreen
import org.pointyware.painteddogs.core.ui.HomeScreen
import org.pointyware.painteddogs.core.ui.ProfileScreen
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
        LocationRoot(
            navController = navController,
            startLocation = null,
            modifier = modifier,
        ) {
            // user home screen; entry point
            location(null) {
                HomeScreen()
            }
            // a user needs to control how they appear to others
            location("users/\$id/profile") {
                ProfileScreen()
            }

            // we need to make a collection before anyone can contribute
            location("collections/create") {
                CollectionDetailsScreen()
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
                ContributionHistoryScreen()
            }
        }
    }
}
