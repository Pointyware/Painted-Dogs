package org.pointyware.painteddogs.feature.funds.remote

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Fund

/**
 *
 */
class GCloudFundApi(
    private val ktorClient: HttpClient,
): FundApi {
    override suspend fun startDonationDrive(
        title: String,
        description: String,
        targetAmount: CurrencyAmount
    ): Result<Fund> {
        TODO("Not yet implemented")
    }

    override suspend fun search(query: String): Result<List<Fund>> {
        TODO("Not yet implemented")
    }

    override suspend fun update(fund: Fund): Result<Fund> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(fund: Fund): Result<Unit> {
        TODO("Not yet implemented")
    }
}
