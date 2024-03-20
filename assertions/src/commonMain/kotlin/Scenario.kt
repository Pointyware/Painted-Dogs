package org.pointyware.painteddogs.assertions

/**
 * Represents a scenario in which a subject is tested.
 */
class Scenario<T: Any>(
    val subject: T
) {
    /**
     * Establishes pre-conditions for the scenario.
     */
    fun given(block: Condition<T>.() -> Unit): Precondition<T> {
        return Precondition(subject, block)
    }
}

class Precondition<T: Any>(
    val subject: T,
    val condition: Condition<T>.() -> Unit
) {
    /**
     * Executes the actions to test the subject.
     */
    fun `when`(actions: T.() -> Unit): TestResult<T> {
        try {
            assert().that(subject).condition()
        } catch (e: Throwable) {
            return PassingTestResult()
        }
        actions(subject)
        return AssertingTestResult(subject)
    }
}

interface TestResult<T: Any> {
    fun then(conditions: Condition<T>.() -> Unit)
}

class PassingTestResult<T:Any>: TestResult<T> {
    override fun then(conditions: Condition<T>.() -> Unit) {
        // no-op
    }
}

class AssertingTestResult<T: Any>(
    val subject: T
): TestResult<T> {
    /**
     * Establishes post-conditions for the scenario.
     */
    override fun then(conditions: Condition<T>.() -> Unit) {
        assert().that(subject).conditions()
    }
}
