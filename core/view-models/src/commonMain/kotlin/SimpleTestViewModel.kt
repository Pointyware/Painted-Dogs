package org.pointyware.painteddogs.core.viewmodels

import kotlinx.coroutines.flow.StateFlow

/**
 * Exposes a setter for the state of type [S], the reflected state, a consumer for events of
 * type [E], and a log of events.
 */
interface SimpleTestViewModel<out S, out E> {
    /**
     * Sets the view model state.
     */
    fun setState(state: @UnsafeVariance S)

    /**
     * Retrieves the view model state.
     */
    val simpleState: StateFlow<S>

    /**
     * Handles an event and adds it to the event log.
     */
    fun handleEvent(event: @UnsafeVariance E)

    /**
     * A complete list of all events that have been handled.
     */
    val eventLog: List<E>
}
