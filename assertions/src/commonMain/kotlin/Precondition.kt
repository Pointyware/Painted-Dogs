package org.pointyware.painteddogs.assertions

import kotlin.test.assertTrue

/**
 *
 */
interface PreCondition<T> {
    val subject: T
}
data class AnyPreCondition(
    override val subject: Any
): PreCondition<Any> {
}
data class IntPreCondition(
    override val subject: Int
): PreCondition<Int> {
    /**
     * Is greater than
     */
    fun isGreaterThan(value: Int) {
        assertTrue(subject > value, "$subject is not greater than $value")
    }
}
data class DoublePreCondition(
    override val subject: Double
): PreCondition<Double> {
    /**
     * Is greater than
     */
    fun isGreaterThan(value: Int) {
        assertTrue(subject > value, "$subject is not greater than $value")
    }

    fun isAtMost(value: Int) {
        assertTrue(subject <= value, "$subject is not at most $value")
    }
}
object TestAssumptions: Assumptions {
    override fun that(subject: Int): IntPreCondition {
        return IntPreCondition(subject)
    }

    override fun that(subject: Double): DoublePreCondition {
        return DoublePreCondition(subject)
    }
}
