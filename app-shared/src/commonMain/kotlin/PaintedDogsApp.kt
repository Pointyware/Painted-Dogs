package org.pointyware.painteddogs.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.core.navigation.LocationRoot
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import org.pointyware.painteddogs.feature.funds.navigation.fundsRoute
import org.pointyware.painteddogs.feature.funds.navigation.fundsRouting
import org.pointyware.painteddogs.feature.funds.navigation.getFundsCreationPath
import org.pointyware.painteddogs.feature.funds.navigation.getFundsSearchPath
import org.pointyware.painteddogs.feature.profiles.navigation.getUserProfilePath
import org.pointyware.painteddogs.feature.profiles.navigation.profileRouting
import org.pointyware.painteddogs.shared.di.AppDependencies
import org.pointyware.painteddogs.shared.home.homeRoute
import org.pointyware.painteddogs.shared.home.homeRouting

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
        val currentLocation = navController.currentLocation.collectAsState()
        Scaffold(
            modifier = modifier,
            topBar = {
                CenterAlignedTopAppBar(
//                    colors = TopAppBarColors(
//                        containerColor =
//                        navigationIconContentColor =
//                        titleContentColor =
//                        actionIconContentColor =
//                        scrolledContainerColor =
//                    ),
                    navigationIcon = {
                        val stack = navController.backList.collectAsState()
                        if (stack.value.isNotEmpty()) {
                            IconButton(onClick = { navController.goBack() }) {
                                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Profile")
                            }
                        }
                    },
                    title = {
                        Text(currentLocation.value.toString() ?: "Painted Dogs")
                    },
                    actions = {
                        val userId: Uuid = Uuid.v4() // TODO: get actual user/id from active user
                        IconButton(onClick = { navController.navigateTo(getUserProfilePath(userId)) }) {
                            Icon(Icons.Default.AccountBox, contentDescription = "Profile")
                        }
                        IconButton(onClick = { navController.navigateTo(getFundsSearchPath()) }) {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                    },
                )
            },
            floatingActionButton = {
                when (currentLocation.value) {
                    homeRoute,
                    fundsRoute -> {
                        IconButton(onClick = {
                            navController.navigateTo(getFundsCreationPath())
                        }) {
                            Icon(Icons.Default.AddCircle, contentDescription = "Create Fund")
                        }
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.End,
//            bottomBar = {
//                NavigationBar(
////                    containerColor =
////                    contentColor =
//                ) {
//                    IconButton(
//                        modifier = Modifier.weight(1f),
//                        onClick = { navController.navigateTo(route("funds")) }
//                    ) {
//                        Icon(Icons.Default.Build, contentDescription = "Create Fund")
//                    }
//                    IconButton(
//                        modifier = Modifier.weight(1f),
//                        onClick = { navController.navigateTo(route("rides")) }
//                    ) {
//                        Icon(Icons.Default.Call, contentDescription = "Rides")
//                    }
//                    IconButton(
//                        modifier = Modifier.weight(1f),
//                        onClick = { navController.navigateTo(route("social")) }
//                    ) {
//                        Icon(Icons.Default.Person, contentDescription = "Social")
//                    }
//                }
//            }
        ) { paddingValues ->
            LocationRoot(
                navController = navController,
                modifier = Modifier.padding(paddingValues),
            ) {
                homeRouting()

                profileRouting(
                    onLogout = ::logout
                )

                fundsRouting()

                // Add more routing here
            }
        }
    }
}

fun logout() {
    println("Logging out")
    println("Logged out")
}
