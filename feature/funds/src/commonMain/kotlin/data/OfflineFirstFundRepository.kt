package org.pointyware.painteddogs.feature.funds.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.feature.funds.local.FundCache
import org.pointyware.painteddogs.feature.funds.remote.FundApi
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 */
@OptIn(ExperimentalUuidApi::class)
class OfflineFirstFundRepository(
    private val localDataSource: FundCache,
    private val remoteDataSource: FundApi,
    private val dataScope: CoroutineScope,
): FundRepository {
    override suspend fun startDonationDrive(
        title: String,
        description: String,
        targetAmount: CurrencyAmount
    ): Result<Fund> {
        return remoteDataSource.startDonationDrive(title, description, targetAmount).onSuccess { localDataSource.save(it) }
    }

    override suspend fun findById(id: Uuid): Fund? {
        return localDataSource.findById(id)
    }

    override suspend fun search(query: String): Result<List<Fund>> {
        val cacheSearch = dataScope.async { localDataSource.search(query) }
        val remoteSearch = dataScope.async { remoteDataSource.search(query) }

        return cacheSearch.await().onSuccess { cacheResults ->
            remoteSearch.await().onSuccess { remoteResults ->
                val allResults = cacheResults + remoteResults
                val distinctResults = allResults.distinctBy { it.id }
                return Result.success(distinctResults)
            }
        }
    }

    override suspend fun update(fund: Fund): Result<Fund> {
        val result = remoteDataSource.update(fund)
            .onSuccess { localDataSource.save(it) }
        return result
    }

    override suspend fun delete(fund: Fund): Result<Unit> {
        val result = remoteDataSource.delete(fund)
            .onSuccess { localDataSource.delete(fund) }
        return result
    }
}
