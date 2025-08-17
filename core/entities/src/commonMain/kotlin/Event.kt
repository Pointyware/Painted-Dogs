package org.pointyware.painteddogs.core.entities

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 *
 */
@OptIn(ExperimentalTime::class)
interface Event {
    val start: Instant
    val end: Instant

    val name: String
    val description: String
}

// temporary placeholder
enum class EventType {
    Meeting,
    Strike,
    March
}
