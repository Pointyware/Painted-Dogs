package org.pointyware.painteddogs.chat.navigation

import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.core.navigation.Destination

@Serializable
sealed interface ChatDestination: Destination {
    /**
     * Screen listing a user's chat history.
     */
    @Serializable
    data object History: ChatDestination

}

/**
 *
 */
@Serializable
data object ChatRootDestination: ChatDestination
