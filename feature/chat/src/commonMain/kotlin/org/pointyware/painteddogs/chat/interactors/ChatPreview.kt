package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.entities.Contact

/**
 *
 */
data class ChatPreview(
    val id: String,
    val title: String,
    val participants: List<Contact>,
    val excerpt: String
)
