package org.pointyware.painteddogs.assertions

/**
 * Represents a scenario in which a subject is tested.
 */
data class Scenario<T: Any>(
    val subject: T
)

/**
 * Creates a new test scenario for the given subject, executing the
 * given test block. Catches any [FailedAssumption], skipping the test, and
 * re-throws any other exception to allow normal test failure handling.
 */
fun <T: Any> runTestWith(subject: T, testCase: Scenario<T>.() -> Unit) {
    Scenario(subject).also {
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
