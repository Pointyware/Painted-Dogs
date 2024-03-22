package test

import FundNotFoundException
import FundsRepository
import org.pointyware.painteddogs.core.entities.Fund

/**
 * A simple implementation of [FundsRepository] that stores data in memory.
 */
class TestFundsRepository: FundsRepository {

    private val funds = mutableMapOf<String, Fund>() // Simple in-memory storage

    override suspend fun getFund(collectionId: String): Result<Fund> {
        return funds[collectionId]?.let { Result.success(it) } ?: Result.failure(FundNotFoundException(collectionId))
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

    override suspend fun deleteFund(collectionId: String) {
        funds.remove(collectionId)
    }
}
