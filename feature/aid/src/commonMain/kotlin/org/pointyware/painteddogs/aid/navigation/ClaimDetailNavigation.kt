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
 * Detail screen to define claim parameters in response to
 * a specific [org.pointyware.painteddogs.aid.entities.ResourceOffer]
 * indicated by the given [offerId].
 */
@Serializable
data class ClaimDetailDestination(val offerId: String): AidDestination

/**
 * Navigates to the [ClaimDetailDestination].
 */
fun NavController.navigateToClaimDetail(offerId: String) {
    navigate(ClaimDetailDestination(offerId))
}

@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.claimDetailDestination() {
    composable<ClaimDetailDestination> {
        val idString = it.toRoute<ClaimDetailDestination>().offerId
        val hex = Uuid.parse(idString)
        Text(text = "Claim Detail: ${hex.toHexString()}")
    }
}
