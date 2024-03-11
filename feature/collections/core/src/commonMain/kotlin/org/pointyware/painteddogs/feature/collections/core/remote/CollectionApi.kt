package org.pointyware.painteddogs.feature.collections.core.remote

import kotlinx.coroutines.delay
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.Collection
import org.pointyware.painteddogs.feature.collections.core.crowdfundingCollection

interface CollectionApi {
    suspend fun startDonationDrive(
        title: String,
        description: String,
        targetAmount: CurrencyAmount
    ): Result<Collection>
}

class TestCollectionApi(
    private val defaultDelay: Long = 1000
): CollectionApi {
    override suspend fun startDonationDrive(
        title: String,
        description: String,
        targetAmount: CurrencyAmount
    ): Result<Collection> {
        val newId = Uuid.v4()
        delay(defaultDelay)
        if (targetAmount.amount < 0) return Result.failure(IllegalArgumentException("Target amount must be greater than 0"))
        return Result.success(crowdfundingCollection(newId, title, description, targetAmount))
    }
}
