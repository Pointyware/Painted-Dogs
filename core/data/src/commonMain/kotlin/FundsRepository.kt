package org.pointyware.painteddogs.core.data

import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.Uuid

/**
 * Defines the interface for a repository that manages funds.
 */
interface FundsRepository {
    suspend fun getFund(collectionId: Uuid): Result<Fund>
    suspend fun getFunds(): Result<List<Fund>>
    suspend fun addFund(collection: Fund)
    suspend fun updateFund(collection: Fund): Result<Fund>
    suspend fun deleteFund(collectionId: Uuid)
}

/**
 * @param collectionId The ID of the fund that was requested.
 */
data class FundNotFoundException(val collectionId: Uuid): Throwable("Fund not found: $collectionId")
