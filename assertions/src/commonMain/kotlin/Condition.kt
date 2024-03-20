package org.pointyware.painteddogs.assertions

import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Represents subject-based statements about some system that can be used to establish
 * pre-conditions, post-conditions, and invariants.
 */
open class Condition<T>(
    open val subject: T
) {
    fun isEqualTo(other: T) {
        assertEquals(subject, other, "$subject is not equal to $other")
    }
    fun isNotEqualTo(other: T) {
        assertNotEquals(subject, other, "$subject is equal to $other")
    }
    fun isIn(items: Collection<T>) {
        assertContains(items, subject, "$subject is not in $items")
    }
    fun isNotIn(items: Collection<T>) {
        assertTrue(subject !in items, "$subject is in $items")
    }
    fun isNull() {
        assertNull(subject, "$subject is not null")
    }
}

/**
 * Extends [Condition] to provide additional statements for numbers.
 */
open class NumberCondition<T: Number>(
    override val subject: T
): Condition<T>(subject) {
    fun isGreaterThan(value: T) {
        assertTrue(subject.toDouble() > value.toDouble(), "$subject is not greater than $value")
    }
    fun isAtMost(value: T) {
        assertTrue(subject.toDouble() <= value.toDouble(), "$subject is not at most $value")
    }
}

/**
 * Extends [Condition] to provide additional statements for strings.
 */
data class StringCondition(
    override val subject: String
): Condition<String>(subject) {
    fun isNotEmpty() {
        assertTrue(subject.isNotEmpty(), "String is empty")
    }
}

/**
 * Extends [Condition] to provide additional statements for collections.
 */
data class CollectionCondition<T>(
    override val subject: Collection<T>
): Condition<Collection<T>>(subject) {
    fun contains(item: T) {
        assertTrue(item in subject, "$subject does not contain $item")
    }

    fun doesNotContain(item: T) {
        assertTrue(item !in subject, "$subject contains $item")
    }
}

/**
 * Extends [Condition] to provide additional statements for results.
 */
data class ResultCondition<T>(
    override val subject: Result<T>
): Condition<Result<T>>(subject) {
    fun isFailure() {
        assertTrue(subject.isFailure, "Result is not a failure")
    }

    fun isSuccess() {
        assertTrue(subject.isSuccess, "Result is not a success")
    }
}
