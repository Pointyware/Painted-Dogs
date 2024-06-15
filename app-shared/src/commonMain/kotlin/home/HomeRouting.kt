package org.pointyware.painteddogs.shared.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.core.navigation.Route
import org.pointyware.painteddogs.core.navigation.route
import org.pointyware.painteddogs.shared.di.HomeDependencies

/**
 * Sets up all routes for home navigation.
 */
@Composable
fun LocationRootScope<Route<String>, Any>.homeRouting() {
    val di = remember { getKoin() }
    val profileDependencies = remember { di.get<HomeDependencies>() }

    location(route()) {
        val homeViewModel = remember { profileDependencies.getHomeViewModel() }
        val mapper = remember { profileDependencies.getHomeUiStateMapper() }
        val state = homeViewModel.state.collectAsState()
        HomeScreen(
            state = mapper.map(state.value),
            onFundSelected = { collectionId -> it.navigateTo(route("funds", collectionId)) },
        )
    }
}
