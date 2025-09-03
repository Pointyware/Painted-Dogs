package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavController
import kotlinx.serialization.Serializable


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
