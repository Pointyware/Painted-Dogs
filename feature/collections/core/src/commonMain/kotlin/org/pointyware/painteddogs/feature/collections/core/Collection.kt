package org.pointyware.painteddogs.feature.collections.core

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid

/**
 * Represents a basic collection of funds within the :collections:core module.
 */
class Collection(
    val targetAmount: CurrencyAmount,
    val id: Uuid,
    val type: CollectionType,
    val title: String,
    val description: String
)
