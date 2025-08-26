package org.pointyware.painteddogs.aid.navigation

import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.core.navigation.Destination

sealed interface AidDestination: Destination {
    @Serializable
    data object Board: AidDestination

    @Serializable
    data class Offer(
        val resource: Resource
    ): AidDestination

    @Serializable
    data class Request(
        val resource: Resource
    ): AidDestination
}
