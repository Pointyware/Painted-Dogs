package org.pointyware.painteddogs.feature.profiles.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.feature.funds.ui.ContributionHistoryScreen
import org.pointyware.painteddogs.feature.funds.ui.ContributionHistoryUiStateMapper
import org.pointyware.painteddogs.feature.profiles.ProfileScreen
import org.pointyware.painteddogs.feature.profiles.di.ProfileDependencies

/**
 *
 */
@Composable
fun LocationRootScope<String?>.profileRouting(
    onLogout: () -> Unit,
    onViewCollections: () -> Unit,
    onViewContributions: () -> Unit,
    onViewFund: (fundId:String) -> Unit,
) {
    val di = remember { getKoin() }
    val profileDependencies = remember { di.get<ProfileDependencies>() }

    // a user needs to control how they appear to others
    location("users/\$id") {
        val viewModel = remember { profileDependencies.getProfileViewModel() }
        val mapper = remember { profileDependencies.getProfileUiStateMapper() }
        val state = viewModel.state.collectAsState()
        ProfileScreen(
            state = mapper.map(state.value),
            onEditProfile = viewModel::onEditProfile,
            onViewCollections = onViewCollections,
            onViewContributions = onViewContributions,
            onLogout = onLogout,
        )
    }
    // a user can see all their current and past collections
    location("users/\$id/funds") {
        val viewModel = remember { profileDependencies.getContributionHistoryViewModel() }
        val state = viewModel.state.collectAsState()
        ContributionHistoryScreen(
            state = ContributionHistoryUiStateMapper.map(state.value),
            onViewFund = onViewFund,
        )
    }
}
