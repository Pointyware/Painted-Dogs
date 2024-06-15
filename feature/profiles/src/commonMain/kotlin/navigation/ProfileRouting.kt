package org.pointyware.painteddogs.feature.profiles.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.core.navigation.Route
import org.pointyware.painteddogs.core.navigation.route
import org.pointyware.painteddogs.feature.funds.ui.ContributionHistoryScreen
import org.pointyware.painteddogs.feature.profiles.di.ProfileDependencies
import org.pointyware.painteddogs.feature.profiles.ui.UserProfileView

/**
 * Sets up all routes for profile navigation and defines navigation callbacks.
 */
@Composable
fun LocationRootScope<Route<String>, Any>.profileRouting(
    onLogout: () -> Unit,
) {
    val di = remember { getKoin() }
    val profileDependencies = remember { di.get<ProfileDependencies>() }

    // a user needs to control how they appear to others
    location(route("users", "\$id")) {
        val viewModel = remember { profileDependencies.getProfileViewModel() }
        val mapper = remember { profileDependencies.getProfileUiStateMapper() }
        val state = viewModel.state.collectAsState()
        UserProfileView(
            state = mapper.map(state.value),
            onEditProfile = viewModel::onEditProfile,
            onViewCollections = { userId -> it.navigateTo(route("users", userId.toString(), "funds")) },
            onViewContributions = { userId -> it.navigateTo(route("users", userId.toString(), "contribs")) },
            onLogout = onLogout,
        )
    }
    // a user can see all their current and past collections
    location(route("users", "\$id", "funds")) {
        val viewModel = remember { profileDependencies.getContributionHistoryViewModel() }
        val mapper = remember { profileDependencies.getContributionHistoryUiStateMapper()}
        val state = viewModel.state.collectAsState()
        ContributionHistoryScreen(
            state = mapper.map(state.value),
            onViewFund = { fundId -> it.navigateTo(route("funds", fundId)) },
        )
    }
}
