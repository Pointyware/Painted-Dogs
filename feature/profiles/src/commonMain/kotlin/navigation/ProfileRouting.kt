package org.pointyware.painteddogs.feature.profiles.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.core.navigation.TypeRouterScope
import org.pointyware.painteddogs.feature.funds.navigation.Funds
import org.pointyware.painteddogs.feature.funds.ui.ContributionHistoryScreen
import org.pointyware.painteddogs.feature.profiles.di.ProfileDependencies
import org.pointyware.painteddogs.feature.profiles.ui.UserProfileView


data class Profile(val id: Uuid) {
    inner class Funds
    inner class Contributions
}
/**
 * Sets up all routes for profile navigation and defines navigation callbacks.
 */
@Composable
fun TypeRouterScope.profileRouting(
    onLogout: () -> Unit,
) {
    val di = remember { getKoin() }
    val profileDependencies = remember { di.get<ProfileDependencies>() }

    // a user needs to control how they appear to others
    location(Profile::class) { navController, _ ->
        val viewModel = remember { profileDependencies.getProfileViewModel() }
        val mapper = remember { profileDependencies.getProfileUiStateMapper() }
        val state = viewModel.state.collectAsState()
        UserProfileView(
            state = mapper.map(state.value),
            onEditProfile = viewModel::onEditProfile,
            onViewCollections = { userId ->
                val arg = Profile(id = userId).Funds()
                navController.navigateTo(Profile.Funds::class, arg)
            },
            onViewContributions = { userId ->
                val arg = Profile(id = userId).Contributions()
                navController.navigateTo(Profile.Contributions::class, arg)
            },
            onLogout = onLogout,
        )
    }
    // a user can see all their current and past collections
    location(Profile.Funds::class) { navController, _ ->
        val viewModel = remember { profileDependencies.getContributionHistoryViewModel() }
        val mapper = remember { profileDependencies.getContributionHistoryUiStateMapper()}
        val state = viewModel.state.collectAsState()
        ContributionHistoryScreen(
            state = mapper.map(state.value),
            onViewFund = { fundId ->
                val arg = Funds.FundDetails(fundId)
                navController.navigateTo(Funds.FundDetails::class, arg) },
        )
    }
}
