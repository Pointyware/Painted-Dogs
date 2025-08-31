package org.pointyware.painteddogs.aid.data.fake

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.pointyware.painteddogs.aid.data.AidRepository
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.entities.TemporalScope
import kotlin.coroutines.CoroutineContext
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 *
 */
@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
class FakeAidRepository(
    private val dataContext: CoroutineContext,
    private val dataScope: CoroutineScope,
    private val defaultDelay: Long = 400,
): AidRepository {

    // TODO: maintain offer and requests internally; search for matches on creation

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
            description = description,
            id = Uuid.random()
        ))
    }

    override suspend fun createRequest(
        description: String,
        category: Resource,
        scope: TemporalScope
    ): Result<ResourceRequest> = withContext(dataContext) {
        delay(defaultDelay)
        Result.success(ResourceRequest(
            category = category,
            timePosted = Clock.System.now(),
            scope = scope,
            description = description,
            id = Uuid.random()
        ))
    }
}
