package org.pointyware.painteddogs.feature.funds.test

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.feature.funds.data.FundRepository
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Defines test extensions for the [FundRepository] interface.
 */
interface TestFundRepository: FundRepository {
    var startDonationDriveResponse: Result<Fund>?
}

/**
 * Provides in-memory storage of collections for testing purposes.
 */
@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
class TestFundRepositoryImpl: TestFundRepository {

    var collections: MutableMap<Uuid, Fund> = mutableMapOf()

    override var startDonationDriveResponse: Result<Fund>? = null
    override suspend fun startDonationDrive(title: String, description: String, targetAmount: CurrencyAmount): Result<Fund> {
        startDonationDriveResponse?.let { return it }

        val donationDrive = Fund(
            id = Uuid.random(),
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

    override suspend fun search(query: String): Result<List<Fund>> {
        return Result.success(collections.values.filter { it.title.contains(query, ignoreCase = true) })
    }

    override suspend fun update(fund: Fund): Result<Fund> {
        collections[fund.id] = fund
        return Result.success(fund)
    }

    override suspend fun delete(fund: Fund): Result<Unit> {
        collections.remove(fund.id)
        return Result.success(Unit)
    }
}
