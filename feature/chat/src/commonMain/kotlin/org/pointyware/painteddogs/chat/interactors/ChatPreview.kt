package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.entities.Participant

/**
 *
 */
data class ChatPreview(
    val id: String,
    val title: String,
    val participants: List<Participant>,
    val excerpt: String
)
