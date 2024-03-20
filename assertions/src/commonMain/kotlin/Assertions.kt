package org.pointyware.painteddogs.assertions

/**
 * Base class for declaring assertions to verify test results.
 */
interface Assertions {
    fun <T: Any?> that(subject: T): Condition<T>
    fun <N: Number> that(subject: N): NumberCondition<N>
    fun that(subject: String): StringCondition
    fun <E> that(subject: Collection<E>): CollectionCondition<E>
    fun <E> that(subject: Result<E>): ResultCondition<E>
}

/**
 * Implementation of [Assertions] for tests.
 */
object TestAssertions: Assertions {

    override fun <T : Any?> that(subject: T): Condition<T> {
        return Condition(subject)
    }

    override fun <N : Number> that(subject: N): NumberCondition<N> {
        return NumberCondition(subject)
    }

    override fun that(subject: String): StringCondition {
        return StringCondition(subject)
    }

    override fun <E> that(subject: Collection<E>): CollectionCondition<E> {
        return CollectionCondition(subject)
    }

    override fun <E> that(subject: Result<E>): ResultCondition<E> {
        return ResultCondition(subject)
    }
}

/**
 * Statically available function to declare assertions in tests.
 */
fun assert(): Assertions {
    return TestAssertions
}
