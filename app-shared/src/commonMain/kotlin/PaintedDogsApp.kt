package org.pointyware.painteddogs.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.pointyware.painteddogs.core.navigation.LocationRoot
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import org.pointyware.painteddogs.feature.collections.core.navigation.fundsRouting
import org.pointyware.painteddogs.feature.profiles.navigation.profileRouting
import org.pointyware.painteddogs.shared.di.AppDependencies
import org.pointyware.painteddogs.shared.navigation.homeRouting

/**
 * The main entry point for the Painted Dogs app.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaintedDogsApp(
    dependencies: AppDependencies,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    val navController = dependencies.getNavigationDependencies().getNavController()

    PaintedDogsTheme(
        isDark = isDarkTheme
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
//                    colors = TopAppBarColors()
                    navigationIcon = { },
                    title = { Text("Painted Dogs" /* stringResource(Res.string.app_name) */) },
                    actions = { },
                )
            },
            floatingActionButton = {
                IconButton(onClick = { navController.navigateTo("funds/create") }) {
                    Icon(painter = painterResource(Res.drawable.funds), contentDescription = "Create Fund")
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            bottomBar = {
                NavigationBar(
//                    containerColor =
//                    contentColor =
                ) {
                    IconButton(onClick = { navController.navigateTo("funds") }) {
                        Icon(painter = painterResource(Res.drawable.funds), contentDescription = "Funds")
                    }
                    IconButton(onClick = { navController.navigateTo("rides") }) {
                        Icon(painter = painterResource(Res.drawable.rides), contentDescription = "Rides")
                    }
                    IconButton(onClick = { navController.navigateTo("social") }) {
                        Icon(painter = painterResource(Res.drawable.social), contentDescription = "Social")
                    }
                }
            }
        ) { paddingValues ->
            LocationRoot(
                navController = navController,
                modifier = Modifier.padding(paddingValues),
            ) {
                homeRouting(
                    onCreateFund = { navController.navigateTo("funds/create") },
                    onSearchFunds = { navController.navigateTo("funds/search") },
                    onViewProfile = { navController.navigateTo("users/123/profile") },
                    onFundSelected = { collectionId -> navController.navigateTo("funds/$collectionId") },
                )

                profileRouting(
                    onEditProfile = { navController.navigateTo("users/123/edit") },
                    onLogout = ::logout,
                    onViewCollections = { navController.navigateTo("users/123/funds") },
                    onViewContributions = { navController.navigateTo("users/123/contribs") },
                    onViewFund = { fundId -> navController.navigateTo("funds/$fundId") },
                )

                fundsRouting(
                    onContribute = { navController.navigateTo("funds/123/contribute") },
                    onConfirmFundDetails = { navController.navigateTo("collections/123/contribute") },
                    onConfirmContributionDetails = { navController.navigateTo("funds/123/contribute/confirm") }
                )
            }
        }
    }
}

fun logout() {
    println("Logging out")
    println("Logged out")
}
