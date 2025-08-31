package org.pointyware.painteddogs.aid.entities

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * An offer for a resource.
 */
@OptIn(ExperimentalTime::class)
class ResourceOffer(
    override val category: Resource,
    override val scope: TemporalScope,
    override val description: String,
    override val timePosted: Instant,
): ResourceExchange {
}
