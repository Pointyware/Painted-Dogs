package org.pointyware.painteddogs.aid.entities

import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * A request for a resource.
 */
@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
class ResourceRequest(
    override val id: Uuid,
    override val category: Resource,
    override val scope: TemporalScope,
    override val description: String,
    override val timePosted: Instant
): ResourceExchange {

}
