package org.pointyware.painteddogs.core.entities

import kotlinx.datetime.Instant

/**
 *
 */
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
