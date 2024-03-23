package org.pointyware.painteddogs.feature.collections.core.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.local.CollectionCache
import org.pointyware.painteddogs.feature.collections.core.remote.CollectionApi

/**
 */
class OfflineFirstCollectionRepository(
    private val localDataSource: CollectionCache,
    private val remoteDataSource: CollectionApi,
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
}
