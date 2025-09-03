package org.pointyware.painteddogs.aid.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


/**
 * Detail screen of a [org.pointyware.painteddogs.aid.entities.ResourceOffer]
 * indicated by the given [offerId].
 */
@Serializable
data class OfferDetailDestination(val offerId: String): AidDestination

/**
 * Navigates to the [OfferDetailDestination].
 */
fun NavController.navigateToOfferDetail(offerId: String) {
    navigate(OfferDetailDestination(offerId)) {
        popUpTo(graph.findStartDestination().id)
    }
}
@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.offerDetailDestination() {
    composable<OfferDetailDestination>(
        deepLinks = listOf(
            navDeepLink<OfferDetailDestination>(
                basePath = "pdogs://"
            ) {
                uriPattern = "pdogs://offer/{offerId}"
                action = "android.intent.action.VIEW"
            }
        ),
    )  {
        val idString = it.toRoute<OfferDetailDestination>().offerId
        val hex = Uuid.parse(idString)
        Text(text = "Offer: ${hex.toHexString()}")
    }
}
