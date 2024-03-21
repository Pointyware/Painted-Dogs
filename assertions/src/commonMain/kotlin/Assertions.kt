package org.pointyware.painteddogs.assertions

/**
 * Base class for declaring assertions to verify test results.
 */
interface Assertions {
    fun <T: Any?> that(subject: T): Condition<T>
    fun <N: Number> that(subject: N): NumberCondition<N>
    fun that(subject: String): StringCondition
    fun <E> that(subject: Collection<E>): CollectionCondition<E>
    fun <K, V> that(subject: Map<K, V>): MapCondition<K, V>
    fun <E> that(subject: Result<E>): ResultCondition<E>
}

/**
 * Implementation of [Assertions] for tests.
 */
object TestAssertions: Assertions {
    private val stdAsserter = kotlin.test.asserter

    override fun <T : Any?> that(subject: T): Condition<T> {
        return Condition(subject, stdAsserter)
    }

    override fun <N : Number> that(subject: N): NumberCondition<N> {
        return NumberCondition(subject, stdAsserter)
    }

    override fun that(subject: String): StringCondition {
        return StringCondition(subject, stdAsserter)
    }

    override fun <E> that(subject: Collection<E>): CollectionCondition<E> {
        return CollectionCondition(subject, stdAsserter)
    }

    override fun <K, V> that(subject: Map<K, V>): MapCondition<K, V> {
        return MapCondition(subject, stdAsserter)
    }

    override fun <E> that(subject: Result<E>): ResultCondition<E> {
        return ResultCondition(subject, stdAsserter)
    }
}

/**
 * Statically available function to declare assertions in tests.
 */
fun assert(): Assertions {
    return TestAssertions
}
