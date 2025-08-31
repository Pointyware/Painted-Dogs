package org.pointyware.painteddogs.aid.entities

import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 *
 */
@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
interface ResourceExchange {
    val id: Uuid
    val category: Resource
    val scope: TemporalScope
    val description: String
    val timePosted: Instant
}
