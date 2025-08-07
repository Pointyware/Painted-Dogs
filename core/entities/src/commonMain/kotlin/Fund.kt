package org.pointyware.painteddogs.core.entities

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * Represents a collection of donations.
 * @param title The title of the collection.
 * @param description A description of the collection.
 * @param target The target amount of the collection.
 * @param start The start date of the collection.
 * @param end The end date of the collection.
 */
@OptIn(ExperimentalTime::class)
data class Fund(
    val id: Uuid,
    val title: String,
    val description: String,
    val target: CurrencyAmount?,
    val start: Instant?,
    val end: Instant?,
)
