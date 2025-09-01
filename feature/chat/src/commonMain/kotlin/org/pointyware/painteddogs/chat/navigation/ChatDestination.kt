package org.pointyware.painteddogs.chat.navigation

import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.core.navigation.Destination

@Serializable
sealed interface ChatDestination: Destination {
    /**
     *
     */
    @Serializable
    data object Root: ChatDestination
    /**
     * Screen listing a user's chat history.
     */
    @Serializable
    data object History: ChatDestination

    /**
     * Screen for creating a new chat session.
     */
    @Serializable
    data object New: ChatDestination

    /**
     * Screen for an individual chat session.
     * @param id The unique ID of the specific chat.
     */
    @Serializable
    data class Session(val id: String): ChatDestination
}
