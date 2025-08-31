package org.pointyware.painteddogs.aid.data.fake

import org.pointyware.painteddogs.aid.data.AidRepository
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.TemporalScope
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 *
 */
@OptIn(ExperimentalTime::class)
class FakeAidRepository : AidRepository {
    override suspend fun createOffer(
        description: String,
        category: Resource,
        scope: TemporalScope
    ): Result<ResourceOffer> {
        return Result.success(ResourceOffer(
            category = category,
            timePosted = Clock.System.now(),
            scope = scope,
            description = description
        ))
    }
}
