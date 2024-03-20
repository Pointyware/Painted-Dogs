package org.pointyware.painteddogs.assertions

import kotlin.test.assertTrue

/**
 *
 */
interface PreCondition<T> {
    val subject: T
}
@Deprecated("Use Condition instead", ReplaceWith("Condition"))
data class AnyPreCondition(
    override val subject: Any
): PreCondition<Any> {
}
@Deprecated("Use NumberCondition instead", ReplaceWith("NumberCondition"))
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
@Deprecated("Use NumberCondition instead", ReplaceWith("NumberCondition"))
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

@Deprecated("Use StringCondition instead", ReplaceWith("StringCondition"))
data class StringPrecondition(
    override val subject: String
): PreCondition<String> {
    fun isNotEmpty() {
        assertTrue(subject.isNotEmpty(), "String is empty")
    }
}

@Deprecated("Use CollectionCondition instead", ReplaceWith("CollectionCondition"))
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
