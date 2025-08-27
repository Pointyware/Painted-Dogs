package org.pointyware.painteddogs.aid.entities

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * A request for a resource.
 */
@OptIn(ExperimentalTime::class)
class ResourceRequest(
    override val category: Resource,
    override val description: String,
    override val timePosted: Instant
): ResourceExchange {

}
