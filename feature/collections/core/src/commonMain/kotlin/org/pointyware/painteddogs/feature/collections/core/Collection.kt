package org.pointyware.painteddogs.feature.collections.core

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid

/**
 * Represents a basic collection of funds within the :collections:core module.
 */
open class Collection(
    val targetAmount: CurrencyAmount,
    val id: Uuid,
    val type: CollectionType,
    val title: String,
    val description: String
)

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
