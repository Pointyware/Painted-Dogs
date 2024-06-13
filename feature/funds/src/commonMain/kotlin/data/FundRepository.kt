package org.pointyware.painteddogs.feature.funds.data

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.Uuid

/**
 *
 */
interface FundRepository {
    suspend fun startDonationDrive(title: String, description: String, targetAmount: CurrencyAmount): Result<Fund>
    suspend fun findById(id: Uuid): Fund?
    suspend fun search(query: String): Result<List<Fund>>
}
