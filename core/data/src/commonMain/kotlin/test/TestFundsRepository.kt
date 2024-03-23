package org.pointyware.painteddogs.core.data.test

import kotlinx.coroutines.delay
import org.pointyware.painteddogs.core.data.FundNotFoundException
import org.pointyware.painteddogs.core.data.FundsRepository
import org.pointyware.painteddogs.core.entities.Fund

/**
 * A simple implementation of [FundsRepository] that stores data in memory.
 */
class TestFundsRepository(
    var defaultDelay: Long = 0
): FundsRepository {

    private val funds = mutableMapOf<String, Fund>() // Simple in-memory storage

    private suspend fun delayBefore() {
        if (defaultDelay > 0) {
            delay(defaultDelay)
        }
    }

    override suspend fun getFund(collectionId: String): Result<Fund> {
        delayBefore()
        return funds[collectionId]?.let { Result.success(it) } ?: Result.failure(
            FundNotFoundException(collectionId)
        )
    }

    override suspend fun getFunds(): Result<List<Fund>> {
        delayBefore()
        return Result.success(funds.values.toList())
    }

    override suspend fun addFund(collection: Fund) {
        delayBefore()
        funds[collection.id] = collection
    }

    override suspend fun updateFund(collection: Fund): Result<Fund> {
        delayBefore()
        return funds[collection.id]?.let {
            funds[collection.id] = collection
            Result.success(collection)
        } ?: Result.failure(FundNotFoundException(collection.id))
    }

    override suspend fun deleteFund(collectionId: String) {
        delayBefore()
        funds.remove(collectionId)
    }
}
