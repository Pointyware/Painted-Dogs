package org.pointyware.painteddogs.shared.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.shared.home.HomeUiStateMapper
import org.pointyware.painteddogs.shared.home.HomeViewModel
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
    val koin = getKoin()
    val homeViewModel = koin.get<HomeViewModel>()
    val mapper = koin.get<HomeUiStateMapper>()

    val state = homeViewModel.state.collectAsState()

    location(null) {
        HomeScreen(
            state = mapper.map(state.value),
            onCreateFund = onCreateFund,
            onSearchFunds = onSearchFunds,
            onViewProfile = onViewProfile,
            onFundSelected = onFundSelected,
        )
    }
}
