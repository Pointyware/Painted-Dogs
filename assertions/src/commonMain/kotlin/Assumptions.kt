package org.pointyware.painteddogs.assertions

/**
 * Base class for declaring assumptions to establish test preconditions.
 */
interface Assumptions {
    fun that(subject: Int): IntPreCondition
    fun that(subject: Double): DoublePreCondition
}
fun assume(): Assumptions {
    return TestAssumptions
}
