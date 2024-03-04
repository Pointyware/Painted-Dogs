package org.pointyware.painteddogs.feature.collections.core.data

import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.Collection

/**
 */
interface CollectionRepository {
    fun startDonationDrive(title: String, description: String, targetAmount: Double): Collection
    fun findById(id: Uuid): Collection?
}
