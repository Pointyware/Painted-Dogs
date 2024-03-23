package org.pointyware.painteddogs.feature.collections.core.remote

import kotlinx.coroutines.delay
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.Uuid

interface CollectionApi {
    suspend fun startDonationDrive(
        title: String,
        description: String,
        targetAmount: CurrencyAmount
    ): Result<Fund>

    suspend fun search(query: String): Result<List<Fund>>
}

class TestCollectionApi(
    private val defaultDelay: Long = 1000,
    initialFundList: List<Fund> = emptyList()
): CollectionApi {

    private val funds = initialFundList.toMutableList()
    fun addFund(fund: Fund) {
        funds.add(fund)
    }

    override suspend fun startDonationDrive(
        title: String,
        description: String,
        targetAmount: CurrencyAmount
    ): Result<Fund> {
        val newId = Uuid.v4()
        delay(defaultDelay)
        if (targetAmount.amount < 0) return Result.failure(IllegalArgumentException("Target amount must be greater than 0"))
        return Result.success(Fund(newId, title, description, targetAmount, null, null))
    }

    override suspend fun search(query: String): Result<List<Fund>> {
        return funds.filter { it.title.contains(query, ignoreCase = true) }.let {
            Result.success(it)
        }
    }
}
