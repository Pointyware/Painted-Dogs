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

/**
 * Creates and returns a new scenario for the given subject, executing the
 * given test block. Catches any [FailedAssumption], skipping the test, and
 * re-throws any other exception to allow normal test failure handling.
 */
fun <T: Any> scenario(subject: T, testCase: Scenario<T>.() -> Unit): Scenario<T> {
    return Scenario(subject).also {
        try {
            it.testCase()
        } catch (e: FailedAssumption) {
            // TODO: print info about the skipped case
        } catch (e: Throwable) {
            // TODO: print info about the failed case
            throw e
        }
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
            return SkippedTestResult()
        }
        actions(subject)
        return AssertingTestResult(subject)
    }
}

interface TestResult<T: Any> {
    fun then(conditions: Condition<T>.() -> Unit)
}

class SkippedTestResult<T:Any>: TestResult<T> {
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
