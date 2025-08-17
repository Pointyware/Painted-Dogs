@file:OptIn(ExperimentalUuidApi::class)

package org.pointyware.painteddogs.feature.profiles.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.Destination
import org.pointyware.painteddogs.core.navigation.Path
import org.pointyware.painteddogs.core.navigation.StaticRoute
import org.pointyware.painteddogs.core.navigation.emptyPath
import org.pointyware.painteddogs.core.navigation.pathArgumentPlaceholder
import org.pointyware.painteddogs.feature.funds.navigation.getFundInfoPath
import org.pointyware.painteddogs.feature.funds.ui.ContributionHistoryScreen
import org.pointyware.painteddogs.feature.profiles.di.ProfileDependencies
import org.pointyware.painteddogs.feature.profiles.ui.UserProfileView
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

val usersRoute = StaticRoute("users", Unit)
val userProfileRoute = usersRoute.variable<Uuid>("user-$pathArgumentPlaceholder")
val userFundsRoute = userProfileRoute.fixed("funds")
val userContributionsRoute = userProfileRoute.fixed("contribs")

fun getUserProfilePath(userId: Uuid): Path {
    return userProfileRoute.provide(userId) { usersRoute -> usersRoute.skip { emptyPath() } }
}
@Serializable
data class Profile(val id: Uuid): Destination {
    @Serializable
    inner class Funds: Destination
    inner class Contributions
}

@Deprecated("Replace with Profile.Funds")
fun getUserFundsPath(userId: Uuid): Path = userFundsRoute.skip { it.provide(userId) { userProfileRoute -> userProfileRoute.skip { emptyPath() }}}
fun getUserContributionsPath(userId: Uuid): Path = userContributionsRoute.skip { it.provide(userId) { it.skip { emptyPath()}} }

/**
 * Sets up all routes for profile navigation and defines navigation callbacks.
 */
fun NavGraphBuilder.profileRouting(
    navController: NavController,
    onLogout: () -> Unit,
) {

    // a user needs to control how they appear to others
    composable<Profile> {
        val di = remember { getKoin() }
        val profileDependencies = remember { di.get<ProfileDependencies>() }

        val viewModel = remember { profileDependencies.getProfileViewModel() }
        val mapper = remember { profileDependencies.getProfileUiStateMapper() }
        val state = viewModel.state.collectAsState()
        UserProfileView(
            state = mapper.map(state.value),
            onEditProfile = viewModel::onEditProfile,
            onViewCollections = { userId -> navController.navigate(getUserFundsPath(userId)) },
            onViewContributions = { userId -> navController.navigate(getUserContributionsPath(userId)) },
            onLogout = onLogout,
        )
    }

    // a user can see all their current and past collections
    composable<Profile.Funds> {
        val di = remember { getKoin() }
        val profileDependencies = remember { di.get<ProfileDependencies>() }

        val viewModel = remember { profileDependencies.getContributionHistoryViewModel() }
        val mapper = remember { profileDependencies.getContributionHistoryUiStateMapper()}
        val state = viewModel.state.collectAsState()
        ContributionHistoryScreen(
            state = mapper.map(state.value),
            onViewFund = { fundId -> navController.navigate(getFundInfoPath(fundId)) },
        )
    }
}
