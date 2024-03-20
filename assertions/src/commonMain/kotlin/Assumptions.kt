package org.pointyware.painteddogs.assertions

/**
 * Base class for declaring assumptions to establish test preconditions.
 */
interface Assumptions {
    fun <T: Any> that(subject: T): Condition<T>
    fun <N: Number> that(subject: N): NumberCondition<N>
    fun that(subject: String): StringCondition
    fun <E> that(subject: Collection<E>): CollectionCondition<E>
    fun <E> that(subject: Result<E>): ResultCondition<E>
}

/**
 * Implementation of [Assumptions] for tests.
 */
object TestAssumptions: Assumptions {
    override fun <T : Any> that(subject: T): Condition<T> {
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
 * Statically available function to declare assumptions in tests.
 */
fun assume(): Assumptions {
    return TestAssumptions
}
