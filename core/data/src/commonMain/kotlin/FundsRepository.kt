package org.pointyware.painteddogs.core.data

import org.pointyware.painteddogs.core.entities.Fund
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Defines the interface for a repository that manages funds.
 */
@OptIn(ExperimentalUuidApi::class)
interface FundsRepository {
    suspend fun getFund(collectionId: Uuid): Result<Fund>
    suspend fun getFunds(): Result<List<Fund>>
    suspend fun addFund(collection: Fund)
    suspend fun updateFund(collection: Fund): Result<Fund>
    suspend fun deleteFund(collectionId: Uuid)
}

/**
 * @param fundId The ID of the fund that was requested.
 */
@OptIn(ExperimentalUuidApi::class)
data class FundNotFoundException(val fundId: Uuid): Throwable("Fund not found: $fundId")
