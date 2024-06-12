package org.pointyware.painteddogs.core.common

/**
 *
 */
interface Mapper<I, O> {
    fun map(input: I): O
}
