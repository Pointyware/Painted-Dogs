package org.pointyware.painteddogs.feature.collections.core.test

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.data.FundRepository

/**
 * Provides in-memory storage of collections for testing purposes.
 */
class TestFundRepository: FundRepository {

    var collections: MutableMap<Uuid, Fund> = mutableMapOf()

    override suspend fun startDonationDrive(title: String, description: String, targetAmount: CurrencyAmount): Result<Fund> {
        val donationDrive = Fund(
            id = Uuid.v4(),
            title = title,
            description = description,
            target = targetAmount,
            start = null,
            end = null,
        )
        collections[donationDrive.id] = donationDrive
        return Result.success(donationDrive)
    }

    override suspend fun findById(id: Uuid): Fund? {
        return collections[id]
    }
}
