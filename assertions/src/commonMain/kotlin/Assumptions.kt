package org.pointyware.painteddogs.assertions

/**
 * Base class for declaring assumptions to establish test preconditions.
 */
interface Assumptions {
    fun that(subject: Int): IntPreCondition
    fun that(subject: Double): DoublePreCondition
    fun that(subject: String): StringPrecondition
    fun <E> that(subject: Collection<E>): CollectionPrecondition<E>
}

/**
 * Implementation of [Assumptions] for tests.
 */
object TestAssumptions: Assumptions {
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
 * Statically available function to declare assumptions in tests.
 */
fun assume(): Assumptions {
    return TestAssumptions
}
