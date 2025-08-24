package org.pointyware.painteddogs.chat.entities

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 *
 */
@OptIn(ExperimentalTime::class)
data class Message(
    val participant: Contact,
    val content: String,
    val time: Instant
)
