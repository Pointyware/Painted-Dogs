package org.pointyware.painteddogs.assertions

import kotlin.test.assertTrue

/**
 * TODO: convert/merge with to (Pre)Condition classes
 */
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

/**
 * Base class for declaring assertions to verify test results.
 */
interface Assertions {
    fun that(subject: Int): IntPreCondition
    fun that(subject: Double): DoublePreCondition
    fun that(subject: String): StringPrecondition
    fun <E> that(subject: Collection<E>): CollectionPrecondition<E>
}

/**
 * Implementation of [Assertions] for tests.
 */
object TestAssertions: Assertions {
    override fun that(subject: Int): IntPreCondition {
        return IntPreCondition(subject)
    }

    override fun that(subject: Double): DoublePreCondition {
        return DoublePreCondition(subject)
    }

    override fun that(subject: String): StringPrecondition {
        return StringPrecondition(subject)
    }

    override fun <E> that(subject: Collection<E>): CollectionPrecondition<E> {
        return CollectionPrecondition(subject)
    }
}

/**
 * Statically available function to declare assertions in tests.
 */
fun <T> assert(): Assertions {
    return TestAssertions
}
