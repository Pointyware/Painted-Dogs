package org.pointyware.painteddogs.shared.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.shared.di.HomeDependencies
import org.pointyware.painteddogs.shared.screens.HomeScreen

/**
 * Sets up all routes for home navigation.
 */
@Composable
fun LocationRootScope<String?>.homeRouting(
    onCreateFund: () -> Unit,
    onSearchFunds: () -> Unit,
    onViewProfile: () -> Unit,
    onFundSelected: (String) -> Unit,
) {
    val di = remember { getKoin() }
    val profileDependencies = remember { di.get<HomeDependencies>() }

    location(null) {
        val homeViewModel = remember { profileDependencies.getHomeViewModel() }
        val mapper = remember { profileDependencies.getHomeUiStateMapper() }
        val state = homeViewModel.state.collectAsState()
        HomeScreen(
            state = mapper.map(state.value),
            onCreateFund = onCreateFund,
            onSearchFunds = onSearchFunds,
            onViewProfile = onViewProfile,
            onFundSelected = onFundSelected,
        )
    }
}
