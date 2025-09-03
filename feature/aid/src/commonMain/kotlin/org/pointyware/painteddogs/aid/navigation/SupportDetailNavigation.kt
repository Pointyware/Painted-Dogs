package org.pointyware.painteddogs.aid.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


/**
 * Detail screen to define support parameters in response to
 * a specific [org.pointyware.painteddogs.aid.entities.ResourceRequest]
 * indicated by the given [requestId].
 */
@Serializable
data class SupportDetailDestination(val requestId: String): AidDestination

/**
 * Navigates to the [SupportDetailDestination].
 */
fun NavController.navigateToSupportDetail(requestId: String) {
    navigate(SupportDetailDestination(requestId))
}

@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.supportDetailDestination() {
    composable<SupportDetailDestination> {
        val idString = it.toRoute<SupportDetailDestination>().requestId
        val hex = Uuid.parse(idString)
        Text(text = "Support Detail: ${hex.toHexString()}")
    }
}
