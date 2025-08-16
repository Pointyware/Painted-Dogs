package org.pointyware.painteddogs.core.entities

/**
 * TODO: describe purpose/intent of Business
 */
interface Business {
    val id: Uuid
    val name: String
    val address: Address
    val positions: Set<Occupation>
    fun compensationFor(occupation: Occupation): CompensationRange
}
