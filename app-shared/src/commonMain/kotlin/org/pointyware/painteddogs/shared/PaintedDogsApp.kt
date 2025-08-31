package org.pointyware.painteddogs.shared

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.pointyware.painteddogs.aid.navigation.AidDestination
import org.pointyware.painteddogs.aid.navigation.aidRouting
import org.pointyware.painteddogs.chat.navigation.ChatDestination
import org.pointyware.painteddogs.chat.navigation.chatRouting
import org.pointyware.painteddogs.core.navigation.navTypeMap
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import org.pointyware.painteddogs.feature.funds.navigation.fundsRouting
import org.pointyware.painteddogs.feature.profiles.navigation.profileRouting
import org.pointyware.painteddogs.shared.home.homeRouting
import org.pointyware.painteddogs.shared.ui.PaintedDogsBottomBar
import org.pointyware.painteddogs.shared.ui.PaintedDogsTopBar
import kotlin.uuid.ExperimentalUuidApi

/**
 * The main entry point for the Painted Dogs app.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalUuidApi::class)
@Composable
fun PaintedDogsApp(
    useDynamicColors: Boolean,
    isDarkTheme: Boolean,
) {
    val navController = rememberNavController()

    PaintedDogsTheme(
        useDynamicColors = useDynamicColors,
        isDark = isDarkTheme
    ) {
        Scaffold(
            topBar = { PaintedDogsTopBar(navController) },
            bottomBar = { PaintedDogsBottomBar(
                onNavigateToChat = {
                    navController.navigate(ChatDestination())
                },
                onNavigateToAid = {
                    navController.navigate(AidDestination())
                }
            ) }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                startDestination = ChatDestination(),
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
                navigation<ChatDestination>(
                    startDestination = ChatDestination.History
                ) {
                    chatRouting(navController)
                }
                navigation<AidDestination>(
                    startDestination = AidDestination.Board
                ) {
                    aidRouting(navController)
                }

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
