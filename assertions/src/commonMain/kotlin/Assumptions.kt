package org.pointyware.painteddogs.assertions

import kotlin.test.Asserter

/**
 * Base class for declaring assumptions to establish test preconditions.
 */
interface Assumptions {
    fun <T: Any> that(subject: T): Condition<T>
    fun <N: Number> that(subject: N): NumberCondition<N>
    fun that(subject: String): StringCondition
    fun <E> that(subject: Collection<E>): CollectionCondition<E>
    fun <E> that(subject: Result<E>): ResultCondition<E>
    companion object {
        /**
         * Statically available function to declare assumptions in tests.
         */
        fun assume(): Assumptions {
            return TestAssumptions
        }
    }
}

class FailedAssumption(msg: String?, cause: Throwable?): Throwable(msg, cause)

/**
 * Implementation of [Assumptions] for tests.
 */
object TestAssumptions: Assumptions {
    private val presumptuousAsserter = object: Asserter {
        override fun fail(message: String?): Nothing {
            throw FailedAssumption("Assumption failed - $message", null)
        }

        override fun fail(message: String?, cause: Throwable?): Nothing {
            throw FailedAssumption("Assumption failed - $message", cause)
        }
    }
    override fun <T : Any> that(subject: T): Condition<T> {
        return Condition(subject, presumptuousAsserter)
    }

    override fun <N : Number> that(subject: N): NumberCondition<N> {
        return NumberCondition(subject, presumptuousAsserter)
    }

    override fun that(subject: String): StringCondition {
        return StringCondition(subject, presumptuousAsserter)
    }

    override fun <E> that(subject: Collection<E>): CollectionCondition<E> {
        return CollectionCondition(subject, presumptuousAsserter)
    }

    override fun <E> that(subject: Result<E>): ResultCondition<E> {
        return ResultCondition(subject, presumptuousAsserter)
    }
}
