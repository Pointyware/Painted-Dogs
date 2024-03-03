package org.pointyware.painteddogs.core.entities

import kotlin.random.Random

/**
 *
 */
data class Uuid(
    private val bytes: ByteArray = ByteArray(16)
) {

    companion object {
        fun v4(): Uuid {
            return Uuid(Random.nextBytes(16))
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Uuid

        return bytes.contentEquals(other.bytes)
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }
}
