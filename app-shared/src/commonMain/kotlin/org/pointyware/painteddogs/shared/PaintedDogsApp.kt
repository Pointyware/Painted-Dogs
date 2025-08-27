package org.pointyware.painteddogs.shared

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import org.pointyware.painteddogs.aid.navigation.AidDestination
import org.pointyware.painteddogs.aid.navigation.aidRouting
import org.pointyware.painteddogs.chat.navigation.ChatDestination
import org.pointyware.painteddogs.chat.navigation.chatRouting
import org.pointyware.painteddogs.core.navigation.navTypeMap
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import org.pointyware.painteddogs.feature.funds.navigation.fundsRouting
import org.pointyware.painteddogs.feature.profiles.navigation.profileRouting
import org.pointyware.painteddogs.shared.home.Home
import org.pointyware.painteddogs.shared.home.homeRouting
import kotlin.uuid.ExperimentalUuidApi

/**
 * The main entry point for the Painted Dogs app.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalUuidApi::class)
@Composable
fun PaintedDogsApp(
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
                        val titleRes = when(location) {
                            Home -> Res.string.app_name
                            else -> null
                        }
                        Text(titleRes?.let { stringResource(it) } ?: "Generated: $location")
                    },
                    actions = {

                    },
                )
            },
            bottomBar = {
                BottomAppBar {
                    Button(
                        onClick = {
                            navController.navigate(ChatDestination.History)
                        }
                    ) {
                        Text(
                            text = stringResource(Res.string.label_chat)
                        )
                    }
                    Button(
                        onClick = {
                            navController.navigate(AidDestination.Board)
                        }
                    ) {
                        Text(
                            text = stringResource(Res.string.label_aid)
                        )
                    }
                }
            }
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
                aidRouting(navController)
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
