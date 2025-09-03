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
import org.pointyware.painteddogs.aid.navigation.AidRootDestination
import org.pointyware.painteddogs.aid.navigation.ExchangeBoardDestination
import org.pointyware.painteddogs.aid.navigation.aidRouting
import org.pointyware.painteddogs.aid.navigation.navigateToAidRoot
import org.pointyware.painteddogs.aid.navigation.navigateToClaimDetail
import org.pointyware.painteddogs.aid.navigation.navigateToOfferDetail
import org.pointyware.painteddogs.aid.navigation.navigateToOfferDraft
import org.pointyware.painteddogs.aid.navigation.navigateToRequestDetail
import org.pointyware.painteddogs.aid.navigation.navigateToSupportDetail
import org.pointyware.painteddogs.chat.navigation.ChatHistoryDestination
import org.pointyware.painteddogs.chat.navigation.ChatRootDestination
import org.pointyware.painteddogs.chat.navigation.chatRouting
import org.pointyware.painteddogs.chat.navigation.navigateToChatRoot
import org.pointyware.painteddogs.chat.navigation.navigateToChatSession
import org.pointyware.painteddogs.chat.navigation.navigateToNewChat
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
                    navController.navigateToChatRoot()
                },
                onNavigateToAid = {
                    navController.navigateToAidRoot()
                }
            ) }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                startDestination = ChatRootDestination,
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
                navigation<ChatRootDestination>(
                    startDestination = ChatHistoryDestination
                ) {
                    chatRouting(
                        onNavigateToNewChat = navController::navigateToNewChat,
                        onNavigateToChatSession = navController::navigateToChatSession,
                    )
                }
                navigation<AidRootDestination>(
                    startDestination = ExchangeBoardDestination
                ) {
                    aidRouting(
                        onSupportRequest = navController::navigateToSupportDetail,
                        onClaimOffer = navController::navigateToClaimDetail,
                        onNavigateToRequest =  navController::navigateToRequestDetail,
                        onNavigateToOffer = navController::navigateToOfferDetail,
                        onCreateOffer = navController::navigateToOfferDraft
                    )
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
