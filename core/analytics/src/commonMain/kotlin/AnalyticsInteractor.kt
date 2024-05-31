package org.pointyware.painteddogs.core.analytics

/**
 * Interactor for logging analytics events.
 */
interface AnalyticsInteractor {
    fun setDefaultArguments(defaults: Map<String, Any>)
    fun logEvent(eventName: String, eventParams: Map<String, Any>)
}

/**
 * Simple println() implementation of [AnalyticsInteractor].
 */
class SimpleAnalyticsInteractor(
): AnalyticsInteractor {

    private var defaults: Map<String, Any> = emptyMap()

    override fun setDefaultArguments(defaults: Map<String, Any>) {
        this.defaults = defaults
    }

    override fun logEvent(eventName: String, eventParams: Map<String, Any>) {
        val arguments = defaults + eventParams
        println("Event: $eventName; Arguments: $arguments")
    }
}
