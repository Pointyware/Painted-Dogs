package org.pointyware.painteddogs.feature.collections.crowdfunding

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.Collection
import org.pointyware.painteddogs.feature.collections.core.CollectionType

/**
 * Represents a crowdfunding collection within the :collections:crowdfunding module. Creates a Collection instance.
 */
fun crowdfundingCollection(
    id: Uuid,
    title: String,
    description: String,
    targetAmount: CurrencyAmount
) = Collection(
    targetAmount = targetAmount,
    id = id,
    type = CollectionType.CROWDFUNDING,
    title = title,
    description = description
)
