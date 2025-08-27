package org.pointyware.painteddogs.aid.data

interface AidRepository {
    suspend fun createOffer(): Result<Unit>
}

class AidRepositoryImpl: AidRepository {
    override suspend fun createOffer(): Result<Unit> {
        TODO("Not yet implemented")
    }
}
