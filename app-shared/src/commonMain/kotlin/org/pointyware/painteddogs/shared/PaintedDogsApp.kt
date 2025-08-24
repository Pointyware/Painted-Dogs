package org.pointyware.painteddogs.shared

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.shadow
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.chat.navigation.ChatDestination
import org.pointyware.painteddogs.chat.navigation.chatRouting
import org.pointyware.painteddogs.core.navigation.navTypeMap
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import org.pointyware.painteddogs.feature.funds.navigation.Funds
import org.pointyware.painteddogs.feature.funds.navigation.fundsRouting
import org.pointyware.painteddogs.feature.profiles.navigation.ProfileDestination
import org.pointyware.painteddogs.feature.profiles.navigation.profileRouting
import org.pointyware.painteddogs.shared.di.AppDependencies
import org.pointyware.painteddogs.shared.home.homeRouting
import kotlin.uuid.ExperimentalUuidApi

/**
 * The main entry point for the Painted Dogs app.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalUuidApi::class)
@Composable
fun PaintedDogsApp(
    dependencies: AppDependencies,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    PaintedDogsTheme(
        isDark = isDarkTheme
    ) {
        val currentLocation = navController.currentBackStackEntryAsState()
        Scaffold(
            modifier = modifier,
            topBar = {
                CenterAlignedTopAppBar(
                    modifier = Modifier.shadow(elevation = GeometryTokens.dpMedium),
//                    colors = TopAppBarColors(
//                        containerColor =
//                        navigationIconContentColor =
//                        titleContentColor =
//                        actionIconContentColor =
//                        scrolledContainerColor =
//                    ),
                    navigationIcon = {
                        val stack = navController.currentBackStack.collectAsState()
                        if (stack.value.isNotEmpty()) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Profile")
                            }
                        }
                    },
                    title = {
                        val location = currentLocation.value
                        val titleRes = Res.string.app_name
//                        when(location) {
//                            Home -> Res.string.app_name
//                            FundInfo -> Res.string.app_name
//                            else -> null
//                        }
                        Text(titleRes?.let { stringResource(it) } ?: "Generated: $location")
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(ProfileDestination.User) }) {
                            Icon(Icons.Default.AccountBox, contentDescription = "Profile")
                        }
                        IconButton(onClick = { navController.navigate(Funds.Search) }) {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                    },
                )
            },
            floatingActionButton = {
                when (currentLocation.value) {
                    Funds -> {
                        IconButton(onClick = {
                            navController.navigate(Funds.Create)
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
            NavHost(
                navController = navController,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                startDestination = ChatDestination.History,
                enterTransition = {
                    // Animation used for the entering new Screen
                    EnterTransition.None
                },
                exitTransition = {
                    // Animation used for the existing Screen being replaced
                    ExitTransition.None
                },
                popEnterTransition = {
                    // Animation used for the previous Screen being revealed
                    EnterTransition.None
                },
                popExitTransition = {
                    // Animation used for the latest Screen being removed
                    ExitTransition.None
                },
                sizeTransform = {
                    //
                    null
                },
                typeMap = navTypeMap()
            ) {
                chatRouting(navController)

                homeRouting(navController)

                profileRouting(
                    navController,
                    onLogout = ::logout
                )

                fundsRouting(navController)
            }
        }
    }
}

fun logout() {
    println("Logging out")
    println("Logged out")
}
