package org.pointyware.painteddogs.core.entities

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Represents a business entity that can be boycotted, promoted, etc.
 */
@OptIn(ExperimentalUuidApi::class)
interface Business {
    val id: Uuid
    val name: String
    val address: Address
    val positions: Set<Occupation>
    fun compensationFor(occupation: Occupation): CompensationRange
}
