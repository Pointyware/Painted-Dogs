package org.pointyware.painteddogs.feature.collections.core.test

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.Collection
import org.pointyware.painteddogs.feature.collections.core.data.FundRepository
import org.pointyware.painteddogs.feature.collections.core.CollectionType

/**
 * Provides in-memory storage of collections for testing purposes.
 */
class TestFundRepository: FundRepository {

    var collections: MutableMap<Uuid, Collection> = mutableMapOf()

    override suspend fun startDonationDrive(title: String, description: String, targetAmount: CurrencyAmount): Result<Collection> {
        val donationDrive = Collection(
            id = Uuid.v4(),
            type = CollectionType.CROWDFUNDING,
            title = title,
            description = description,
            targetAmount = targetAmount
        )
        collections[donationDrive.id] = donationDrive
        return Result.success(donationDrive)
    }

    override suspend fun findById(id: Uuid): Collection? {
        return collections[id]
    }
}
