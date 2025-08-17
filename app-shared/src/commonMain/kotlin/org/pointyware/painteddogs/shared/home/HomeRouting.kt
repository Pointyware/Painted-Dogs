package org.pointyware.painteddogs.shared.home

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.Destination
import org.pointyware.painteddogs.feature.funds.navigation.Funds
import org.pointyware.painteddogs.shared.di.HomeDependencies
import kotlin.uuid.ExperimentalUuidApi

@Serializable
data object Home: Destination
/**
 * Sets up all routes for home navigation.
 */
@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.homeRouting(
    navController: NavController
) {
    composable<Home> {
        val di = remember { getKoin() }
        val profileDependencies = remember { di.get<HomeDependencies>() }

        val homeViewModel = remember { profileDependencies.getHomeViewModel() }
        val mapper = remember { profileDependencies.getHomeUiStateMapper() }
        val state = homeViewModel.state.collectAsState()
        HomeScreen(
            state = mapper.map(state.value),
            onFundSelected = { fundId -> navController.navigate(Funds.Info(fundId)) },
        )
    }
}
