package org.pointyware.painteddogs.core.entities

import kotlinx.datetime.Instant

/**
 * Represents a collection of donations.
 * @param title The title of the collection.
 * @param description A description of the collection.
 * @param target The target amount of the collection.
 * @param start The start date of the collection.
 * @param end The end date of the collection.
 */
data class Fund(
    val title: String,
    val description: String,
    val target: CurrencyAmount?,
    val start: Instant?,
    val end: Instant?,
)
