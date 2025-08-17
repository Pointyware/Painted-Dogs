package org.pointyware.painteddogs.feature.funds.data

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Fund
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Defines the interface for a repository that manages funds.
 */
@OptIn(ExperimentalUuidApi::class)
interface FundRepository {
    /**
     * Creates a new fund with the given title, description, and target amount.
     */
    suspend fun startDonationDrive(title: String, description: String, targetAmount: CurrencyAmount): Result<Fund>

    /**
     * Retrieves a fund by its ID, if it exists.
     */
    suspend fun findById(id: Uuid): Fund?

    /**
     * Retrieves a list of funds that match the given query. A match is considered by title first then description.
     */
    suspend fun search(query: String): Result<List<Fund>>

    /**
     * Updates the given fund with the new values. The result contains all allowed changes.
     */
    suspend fun update(fund: Fund): Result<Fund>

    /**
     * Deletes the given fund.
     */
    suspend fun delete(fund: Fund): Result<Unit>
}
