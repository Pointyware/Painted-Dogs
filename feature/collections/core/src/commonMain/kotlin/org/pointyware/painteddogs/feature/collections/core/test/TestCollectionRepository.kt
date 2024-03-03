package org.pointyware.painteddogs.feature.collections.core.test

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.Collection
import org.pointyware.painteddogs.feature.collections.core.CollectionRepository
import org.pointyware.painteddogs.feature.collections.core.CollectionType

/**
 * Provides in-memory storage of collections for testing purposes.
 */
class TestCollectionRepository: CollectionRepository {

    var collections: MutableMap<Uuid, Collection> = mutableMapOf()

    override fun startDonationDrive(title: String, description: String, targetAmount: Double): Collection {
        return Collection(
            id = Uuid.v4(),
            type = CollectionType.DONATION,
            title = title,
            description = description,
            targetAmount = CurrencyAmount(targetAmount)
        )
    }

    override fun findById(id: Uuid): Collection? {
        return collections[id]
    }
}