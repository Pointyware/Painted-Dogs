package org.pointyware.painteddogs.chat.data

import org.pointyware.painteddogs.chat.entities.Contact
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * A single message in a chat log.
 */
@OptIn(ExperimentalTime::class)
data class ChatMessage(
    val id: String,
    val sender: Contact,
    val content: String,
    val timeSent: Instant
)
