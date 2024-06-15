package org.pointyware.painteddogs.feature.funds.remote

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

    suspend fun update(fund: Fund): Result<Fund>
    suspend fun delete(fund: Fund): Result<Unit>
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

    override suspend fun update(fund: Fund): Result<Fund> {
        delay(defaultDelay)
        val index = funds.indexOfFirst { it.id == fund.id }
        if (index == -1) return Result.failure(IllegalArgumentException("Fund not found"))
        funds[index] = fund
        return Result.success(fund)
    }

    override suspend fun delete(fund: Fund): Result<Unit> {
        delay(defaultDelay)
        val index = funds.indexOfFirst { it.id == fund.id }
        if (index == -1) return Result.failure(IllegalArgumentException("Fund not found"))
        funds.removeAt(index)
        return Result.success(Unit)
    }
}
