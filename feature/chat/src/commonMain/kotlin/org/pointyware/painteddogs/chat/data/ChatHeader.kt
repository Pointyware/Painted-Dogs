package org.pointyware.painteddogs.chat.data

/**
 * Data model of a chat session.
 *
 * @param id The unique id of a chat session.
 * @param title The title of the chat session.
 * @param contactIds A list of ids of each contact in the chat session.
 */
data class ChatHeader(
    val id: String,
    val title: String,
    val contactIds: List<String>
)
