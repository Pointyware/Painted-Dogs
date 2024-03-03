package org.pointyware.painteddogs.feature.collections.core

import org.pointyware.painteddogs.core.entities.Uuid

/**
 */
class OfflineFirstCollectionRepository(
    // TODO: add local and remote data sources
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
