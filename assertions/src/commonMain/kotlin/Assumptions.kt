package org.pointyware.painteddogs.assertions

/**
 * Base class for declaring assumptions to establish test preconditions.
 */
interface Assumptions {
    fun that(subject: Int): IntPreCondition
    fun that(subject: Double): DoublePreCondition
}

/**
 * Implementation of [Assumptions] for tests.
 */
object TestAssumptions: Assumptions {
    override fun that(subject: Int): IntPreCondition {
        return IntPreCondition(subject)
    }

    override fun that(subject: Double): DoublePreCondition {
        return DoublePreCondition(subject)
    }
}

/**
 * Statically available function to declare assumptions in tests.
 */
fun assume(): Assumptions {
    return TestAssumptions
}
