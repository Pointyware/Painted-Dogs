package org.pointyware.painteddogs.feature.collections.core.data

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.Collection
import org.pointyware.painteddogs.feature.collections.core.local.CollectionCache
import org.pointyware.painteddogs.feature.collections.core.remote.CollectionApi

/**
 */
class OfflineFirstCollectionRepository(
    private val localDataSource: CollectionCache,
    private val remoteDataSource: CollectionApi
): CollectionRepository {
    override suspend fun startDonationDrive(
        title: String,
        description: String,
        targetAmount: CurrencyAmount
    ): Result<Collection> {
        return remoteDataSource.startDonationDrive(title, description, targetAmount).onSuccess { localDataSource.save(it) }
    }

    override suspend fun findById(id: Uuid): Collection? {
        return localDataSource.findById(id)
    }
}
