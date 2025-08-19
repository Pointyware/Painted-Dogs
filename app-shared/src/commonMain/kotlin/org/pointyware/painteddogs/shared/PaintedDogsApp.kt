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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.chat.navigation.ChatHistory
import org.pointyware.painteddogs.chat.navigation.chatRouting
import org.pointyware.painteddogs.core.navigation.navTypeMap
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import org.pointyware.painteddogs.feature.funds.navigation.FundInfo
import org.pointyware.painteddogs.feature.funds.navigation.Funds
import org.pointyware.painteddogs.feature.funds.navigation.fundsRouting
import org.pointyware.painteddogs.feature.profiles.navigation.UserProfile
import org.pointyware.painteddogs.feature.profiles.navigation.profileRouting
import org.pointyware.painteddogs.shared.di.AppDependencies
import org.pointyware.painteddogs.shared.home.Home
import org.pointyware.painteddogs.shared.home.homeRouting
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

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
                        val location = currentLocation.value
                        val titleRes = when(location) {
                            Home -> Res.string.app_name
                            FundInfo -> Res.string.app_name
                            else -> null
                        }
                        Text(titleRes?.let { stringResource(it) } ?: "Generated: $location")
                    },
                    actions = {
                        val userId: Uuid = Uuid.random() // TODO: get actual user/id from active user
                        IconButton(onClick = { navController.navigateTo(UserProfile(userId)) }) {
                            Icon(Icons.Default.AccountBox, contentDescription = "Profile")
                        }
                        IconButton(onClick = { navController.navigateTo(Funds.Search) }) {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                    },
                )
            },
            floatingActionButton = {
                when (currentLocation.value) {
                    Funds -> {
                        IconButton(onClick = {
                            navController.navigateTo(Funds.Create)
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
            val navCon = rememberNavController()
            NavHost(
                navController = navCon,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                startDestination = ChatHistory,
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
                chatRouting(navCon)

                homeRouting(navCon)

                profileRouting(
                    navCon,
                    onLogout = ::logout
                )

                fundsRouting(navCon)
            }
        }
    }
}

fun logout() {
    println("Logging out")
    println("Logged out")
}
