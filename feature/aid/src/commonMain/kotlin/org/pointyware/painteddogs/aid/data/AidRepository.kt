package org.pointyware.painteddogs.aid.data

import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.entities.TemporalScope
import kotlin.time.ExperimentalTime

interface AidRepository {
    suspend fun createOffer(description: String, category: Resource, scope: TemporalScope): Result<ResourceOffer>
    suspend fun createRequest(
        description: String,
        category: Resource,
        scope: TemporalScope
    ): Result<ResourceRequest>
}

@OptIn(ExperimentalTime::class)
class AidRepositoryImpl: AidRepository {
    override suspend fun createOffer(description: String, category: Resource, scope: TemporalScope): Result<ResourceOffer> {
        TODO("Get Ktor client")
    }

    override suspend fun createRequest(
        description: String,
        category: Resource,
        scope: TemporalScope
    ): Result<ResourceRequest> {
        TODO("Not yet implemented")
    }
}
