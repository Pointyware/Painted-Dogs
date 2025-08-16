package org.pointyware.painteddogs.shared.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.TypeRouterScope
import org.pointyware.painteddogs.feature.funds.navigation.Funds
import org.pointyware.painteddogs.shared.di.HomeDependencies

object Home {}

/**
 * Sets up all routes for home navigation.
 */
@Composable
fun TypeRouterScope.homeRouting() {
    val di = remember { getKoin() }
    val profileDependencies = remember { di.get<HomeDependencies>() }

    location(Home::class) { navController, _ ->
        val homeViewModel = remember { profileDependencies.getHomeViewModel() }
        val mapper = remember { profileDependencies.getHomeUiStateMapper() }
        val state = homeViewModel.state.collectAsState()
        HomeScreen(
            state = mapper.map(state.value),
            onFundSelected = { collectionId ->
                val arg = Funds.FundDetails(collectionId)
                navController.navigateTo(Funds.FundDetails::class, arg)
            },
        )
    }
}
