package org.pointyware.painteddogs.chat.navigation

import org.pointyware.painteddogs.core.navigation.Destination


/**
 * A list of a user's chat history.
 */
data object ChatHistory: Destination

/**
 * An individual chat session.
 * @param id The unique ID of the specific chat.
 */
data class Chat(val id: String): Destination
