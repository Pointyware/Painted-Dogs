package org.pointyware.painteddogs.aid.data.fake

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.pointyware.painteddogs.aid.data.AidRepository
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.TemporalScope
import kotlin.coroutines.CoroutineContext
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 *
 */
@OptIn(ExperimentalTime::class)
class FakeAidRepository(
    private val dataContext: CoroutineContext,
    private val dataScope: CoroutineScope,
    private val defaultDelay: Long = 800,
): AidRepository {
    override suspend fun createOffer(
        description: String,
        category: Resource,
        scope: TemporalScope
    ): Result<ResourceOffer> = withContext(dataContext) {
        delay(defaultDelay)
        Result.success(ResourceOffer(
            category = category,
            timePosted = Clock.System.now(),
            scope = scope,
            description = description
        ))
    }
}
