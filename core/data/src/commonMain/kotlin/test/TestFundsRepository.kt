package org.pointyware.painteddogs.core.data.test

import org.pointyware.painteddogs.core.data.FundNotFoundException
import org.pointyware.painteddogs.core.data.FundsRepository
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.Uuid

/**
 * A simple implementation of [FundsRepository] that stores data in memory.
 */
class TestFundsRepository(
    var defaultDelay: Long = 0
): FundsRepository, BaseTestRepository {

    private val funds = mutableMapOf<Uuid, Fund>() // Simple in-memory storage

    override suspend fun getFund(collectionId: Uuid): Result<Fund> {
        return funds[collectionId]?.let { Result.success(it) } ?: Result.failure(
            FundNotFoundException(collectionId)
        )
    }

    suspend fun getFund(collectionId: Uuid, latency: Long = defaultDelay) = stubResponse(latency) {
        getFund(collectionId)
    }

    override suspend fun getFunds(): Result<List<Fund>> {
        return Result.success(funds.values.toList())
    }

    override suspend fun addFund(collection: Fund) {
        funds[collection.id] = collection
    }

    override suspend fun updateFund(collection: Fund): Result<Fund> {
        return funds[collection.id]?.let {
            funds[collection.id] = collection
            Result.success(collection)
        } ?: Result.failure(FundNotFoundException(collection.id))
    }

    override suspend fun deleteFund(collectionId: Uuid) {
        funds.remove(collectionId)
    }
}
