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

data class StringPrecondition(
    override val subject: String
): PreCondition<String> {
    fun isNotEmpty() {
        assertTrue(subject.isNotEmpty(), "String is empty")
    }
}

data class CollectionPrecondition<T>(
    override val subject: Collection<T>
): PreCondition<Collection<T>> {
    fun contains(item: T) {
        assertTrue(item in subject, "$subject does not contain $item")
    }

    fun doesNotContain(item: T) {
        assertTrue(item !in subject, "$subject contains $item")
    }
}
