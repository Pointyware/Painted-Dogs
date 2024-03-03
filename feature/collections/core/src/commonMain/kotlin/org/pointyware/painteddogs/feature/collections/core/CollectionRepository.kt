package org.pointyware.painteddogs.feature.collections.core

import org.pointyware.painteddogs.core.entities.Uuid

/**
 */
interface CollectionRepository {
    fun startDonationDrive(title: String, description: String, targetAmount: Double): Collection
    fun findById(id: Uuid): Collection?
}
