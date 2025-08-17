@file:OptIn(ExperimentalUuidApi::class)

package org.pointyware.painteddogs.feature.profiles.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.Destination
import org.pointyware.painteddogs.feature.funds.navigation.FundInfo
import org.pointyware.painteddogs.feature.funds.ui.ContributionHistoryScreen
import org.pointyware.painteddogs.feature.profiles.di.ProfileDependencies
import org.pointyware.painteddogs.feature.profiles.ui.UserProfileView
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class UserProfile(val userId: String): Destination {
    constructor(id: Uuid): this(id.toString())

    fun funds() = UserFunds
}

@Serializable
data class UserContributions(val userId: String) {
    constructor(id: Uuid): this(id.toString())
}

@Serializable
class UserFunds(val userId: String): Destination {
    constructor(id: Uuid): this(id.toString())
}

/**
 * Sets up all routes for profile navigation and defines navigation callbacks.
 */
fun NavGraphBuilder.profileRouting(
    navController: NavController,
    onLogout: () -> Unit,
) {

    // a user needs to control how they appear to others
    composable<UserProfile> {
        val di = remember { getKoin() }
        val profileDependencies = remember { di.get<ProfileDependencies>() }

        val viewModel = remember { profileDependencies.getProfileViewModel() }
        val mapper = remember { profileDependencies.getProfileUiStateMapper() }
        val state = viewModel.state.collectAsState()
        UserProfileView(
            state = mapper.map(state.value),
            onEditProfile = viewModel::onEditProfile,
            onViewCollections = { userId -> navController.navigate(UserFunds(userId)) },
            onViewContributions = { userId -> navController.navigate(UserContributions(userId)) },
            onLogout = onLogout,
        )
    }

    // a user can see all their current and past collections
    composable<UserFunds> {
        val di = remember { getKoin() }
        val profileDependencies = remember { di.get<ProfileDependencies>() }

        val viewModel = remember { profileDependencies.getContributionHistoryViewModel() }
        val mapper = remember { profileDependencies.getContributionHistoryUiStateMapper()}
        val state = viewModel.state.collectAsState()
        ContributionHistoryScreen(
            state = mapper.map(state.value),
            onViewFund = { fundId -> navController.navigate(FundInfo(fundId)) },
        )
    }

    composable<UserContributions> {
        Button(
            onClick = { navController.popBackStack() }
        ) {
            Text("go back")
        }
    }
}
