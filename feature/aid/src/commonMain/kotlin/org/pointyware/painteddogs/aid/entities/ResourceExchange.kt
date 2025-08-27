package org.pointyware.painteddogs.aid.entities

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 *
 */
@OptIn(ExperimentalTime::class)
interface ResourceExchange {
    val category: Resource
    val description: String
    val timePosted: Instant
}
