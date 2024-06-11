package org.pointyware.painteddogs.feature.profiles.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.map
import org.koin.compose.getKoin
import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.core.navigation.di.NavigationDependencies
import org.pointyware.painteddogs.feature.collections.core.ui.ContributionHistoryScreen
import org.pointyware.painteddogs.feature.collections.core.ui.ContributionHistoryScreenState
import org.pointyware.painteddogs.core.ui.ProfileScreen
import org.pointyware.painteddogs.core.ui.ProfileScreenState
import org.pointyware.painteddogs.feature.profiles.viewmodels.ProfileViewModel
import org.pointyware.painteddogs.feature.profiles.di.ProfileDependencies
import org.pointyware.painteddogs.feature.profiles.ui.ProfileUiStateMapper

/**
 *
 */
@Composable
fun LocationRootScope<String?>.profileNavigation(
    navigationDependencies: NavigationDependencies,
    profileDependencies: ProfileDependencies
) {
    val navController = remember { navigationDependencies.getNavController() }

    // a user needs to control how they appear to others
    location("users/\$id") {
        val viewModel = getKoin().get<ProfileViewModel>()
        val state = viewModel.state.collectAsState()
        ProfileScreen(
            state = ProfileUiStateMapper.map(state.value),
            onEditProfile = {},
            onViewCollections = { navController.navigateTo("users/123/collections") },
            onViewContributions = { navController.navigateTo("users/123/contributions") },
            onLogout = {},
        )
    }
    // a user can see all their current and past collections
    location("users/\$id/funds") {
        ContributionHistoryScreen(
            state = ContributionHistoryScreenState(
                contributions = emptyList()
            ),
            onViewFund = { fundId -> navController.navigateTo("collections/$fundId") },
        )
    }
}
