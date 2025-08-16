package org.pointyware.painteddogs.core.analytics

/**
 * Interactor for logging analytics events.
 */
interface AnalyticsInteractor {
    /**
     * Sets arbitrary information arguments about a user.
     */
    fun setUserInfo(info: Map<String, Any>)

    /**
     * Sets the default arguments that will be sent with each log event. Subsequent [logEvent] calls
     * will include the given [defaults].
     */
    fun setDefaultArguments(defaults: Map<String, Any>)

    /**
     * Logs an event with the given [name] and [arguments].
     */
    fun logEvent(eventName: String, arguments: Map<String, Any>)
}

/**
 * Simple println() implementation of [AnalyticsInteractor].
 */
class SimpleAnalyticsInteractor(
): AnalyticsInteractor {

    private var userInfo: Map<String, Any> = emptyMap()
    override fun setUserInfo(info: Map<String, Any>) {
        userInfo = info
        println("Set user information: $info")
    }

    private var defaults: Map<String, Any> = emptyMap()
    override fun setDefaultArguments(defaults: Map<String, Any>) {
        this.defaults = defaults
    }

    override fun logEvent(eventName: String, arguments: Map<String, Any>) {
        val finalArgs = defaults + arguments
        println("Event: $eventName; Arguments: $finalArgs")
    }
}
