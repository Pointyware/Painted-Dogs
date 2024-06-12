package org.pointyware.painteddogs.feature.profiles.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.mp.KoinPlatform
import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.core.ui.ProfileScreen
import org.pointyware.painteddogs.feature.collections.core.ui.ContributionHistoryScreen
import org.pointyware.painteddogs.feature.collections.core.ui.ContributionHistoryScreenState
import org.pointyware.painteddogs.feature.profiles.ui.ProfileUiStateMapper
import org.pointyware.painteddogs.feature.profiles.viewmodels.ProfileViewModel

/**
 *
 */
@Composable
fun LocationRootScope<String?>.profileRouting(
    onEditProfile: () -> Unit,
    onLogout: () -> Unit,
    onViewCollections: () -> Unit,
    onViewContributions: () -> Unit,
    onViewFund: (fundId:String) -> Unit,
) {

    // a user needs to control how they appear to others
    location("users/\$id") {
        val viewModel = KoinPlatform.getKoin().get<ProfileViewModel>()
        val state = viewModel.state.collectAsState()
        ProfileScreen(
            state = ProfileUiStateMapper.map(state.value),
            onEditProfile = onEditProfile,
            onViewCollections = onViewCollections,
            onViewContributions = onViewContributions,
            onLogout = onLogout,
        )
    }
    // a user can see all their current and past collections
    location("users/\$id/funds") {
        ContributionHistoryScreen(
            state = ContributionHistoryScreenState(
                contributions = emptyList()
            ),
            onViewFund = onViewFund,
        )
    }
}
