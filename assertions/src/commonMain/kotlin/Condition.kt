package org.pointyware.painteddogs.assertions

import kotlin.test.Asserter
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
    open val subject: T,
    val asserter: Asserter
) {
    fun isEqualTo(other: T) {
        asserter.assertEquals("$subject is not equal to $other", other, subject)
    }
    fun isNotEqualTo(other: T) {
        asserter.assertNotEquals("$subject is equal to $other", other, subject)
    }
    fun isIn(items: Collection<T>) {
        asserter.assertTrue("$subject is not in $items", subject in items)
    }
    fun isNotIn(items: Collection<T>) {
        asserter.assertTrue("$subject is in $items", subject !in items)
    }
    fun isNull() {
        asserter.assertNull("$subject is not null", subject)
    }
}

/**
 * Extends [Condition] to provide additional statements for numbers.
 */
open class NumberCondition<T: Number>(
    override val subject: T,
    asserter: Asserter
): Condition<T>(subject, asserter) {
    fun isGreaterThan(value: T) {
        asserter.assertTrue("$subject is not greater than $value", subject.toDouble() > value.toDouble())
    }
    fun isAtMost(value: T) {
        asserter.assertTrue("$subject is not at most $value", subject.toDouble() <= value.toDouble())
    }
}

/**
 * Extends [Condition] to provide additional statements for strings.
 */
class StringCondition(
    override val subject: String,
    asserter: Asserter
): Condition<String>(subject, asserter) {
    fun isNotEmpty() {
        asserter.assertTrue("String is empty", subject.isNotEmpty())
    }
}

/**
 * Extends [Condition] to provide additional statements for collections.
 */
class CollectionCondition<T>(
    override val subject: Collection<T>,
    asserter: Asserter
): Condition<Collection<T>>(subject, asserter) {
    fun contains(item: T) {
        asserter.assertTrue("$subject does not contain $item", item in subject)
    }

    fun doesNotContain(item: T) {
        asserter.assertTrue("$subject contains $item", item !in subject)
    }
}

/**
 * Extends [Condition] to provide additional statements for maps.
 */
class MapCondition<K, V>(
    override val subject: Map<K, V>,
    asserter: Asserter
): Condition<Map<K, V>>(subject, asserter) {
    fun containsKey(key: K) {
        asserter.assertTrue("$subject does not contain key $key", key in subject)
    }

    fun doesNotContainKey(key: K) {
        asserter.assertTrue("$subject contains key $key", key !in subject)
    }
}

/**
 * Extends [Condition] to provide additional statements for results.
 */
class ResultCondition<T>(
    override val subject: Result<T>,
    asserter: Asserter
): Condition<Result<T>>(subject, asserter) {
    fun isFailure() {
        asserter.assertTrue("Result is not a failure", subject.isFailure)
    }

    fun isSuccess() {
        asserter.assertTrue("Result is not a success", subject.isSuccess)
    }
}
