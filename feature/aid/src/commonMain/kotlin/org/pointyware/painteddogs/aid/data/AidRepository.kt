package org.pointyware.painteddogs.aid.data

import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.entities.TemporalScope
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface AidRepository {
    suspend fun createOffer(description: String, category: Resource, scope: TemporalScope): Result<ResourceOffer>
    suspend fun createRequest(
        description: String,
        category: Resource,
        scope: TemporalScope
    ): Result<ResourceRequest>

    suspend fun getRequest(uuid: Uuid): Result<ResourceRequest>
    suspend fun getOffer(id: Uuid): Result<ResourceOffer>
}

@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
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

    override suspend fun getRequest(uuid: Uuid): Result<ResourceRequest> {
        TODO("Not yet implemented")
    }

    override suspend fun getOffer(id: Uuid): Result<ResourceOffer> {
        TODO("Not yet implemented")
    }
}
