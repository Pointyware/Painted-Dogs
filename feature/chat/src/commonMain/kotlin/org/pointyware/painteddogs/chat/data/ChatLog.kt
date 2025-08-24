package org.pointyware.painteddogs.chat.data

import org.pointyware.painteddogs.chat.entities.Contact

/**
 * A chat log with some or all messages.
 */
class ChatLog(
    val id: String,
    val title: String,
    val participants: List<Contact>,
    val messages: List<ChatMessage>,
    // TODO: add timeCreated
) {
    // TODO: add calculated property to return most recent message time or timeCreated if no messages
}
