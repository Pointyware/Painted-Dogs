package org.pointyware.painteddogs.feature.collections.core.remote

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.Collection
import org.pointyware.painteddogs.feature.collections.core.crowdfundingCollection

interface CollectionApi {
    fun startDonationDrive(
        title: String,
        description: String,
        targetAmount: CurrencyAmount
    ): Result<Collection>
}

class TestCollectionApi : CollectionApi {
    override fun startDonationDrive(
        title: String,
        description: String,
        targetAmount: CurrencyAmount
    ): Result<Collection> {
        val newId = Uuid.v4()
        return Result.success(crowdfundingCollection(newId, title, description, targetAmount))
    }
}
