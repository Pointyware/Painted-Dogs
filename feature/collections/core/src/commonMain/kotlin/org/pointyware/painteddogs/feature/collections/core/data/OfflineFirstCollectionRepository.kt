package org.pointyware.painteddogs.feature.collections.core.data

import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.Collection
import org.pointyware.painteddogs.feature.collections.core.local.CollectionCache
import org.pointyware.painteddogs.feature.collections.core.remote.CollectionApi

/**
 */
class OfflineFirstCollectionRepository(
    // TODO: add local and remote data sources
    private val localDataSource: CollectionCache,
    private val remoteDataSource: CollectionApi
): CollectionRepository {
    override fun startDonationDrive(
        title: String,
        description: String,
        targetAmount: Double
    ): Collection {
        TODO("Submit Donation Creation Request if Network Available; Otherwise create local record to submit later")
    }

    override fun findById(id: Uuid): Collection? {
        TODO("return from confirmed and pending collections")
    }
}
