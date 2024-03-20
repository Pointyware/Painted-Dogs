package org.pointyware.painteddogs.assertions

import kotlin.test.assertTrue


class Subject<T>(private val subject: T) {
    fun isNotIn(items: Collection<T>) {
        assertTrue(subject !in items, "$subject is in $items")
    }

    fun isEqualTo(other: Any) {
        assertTrue(subject == other, "$subject is not equal to $other")
    }

    fun isNull() {
        assertTrue(subject == null, "$subject is not null")
    }

    fun isTrue() {
        assertTrue(subject == true, "$subject is not true")
    }
}
fun <T> assertThat(subject: T): Subject<T> {
    return Subject(subject)
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
data class AnyPreCondition(
    override val subject: Any
): PreCondition<Any> {
}
interface PreCondition<T> {
    val subject: T
}
object TestAssumptions: Assumptions {
    override fun that(subject: Int): IntPreCondition {
        return IntPreCondition(subject)
    }

    override fun that(subject: Double): DoublePreCondition {
        return DoublePreCondition(subject)
    }
}
