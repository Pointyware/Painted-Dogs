package org.pointyware.painteddogs.core.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


/**
 * Gives the ability to navigate amongst locations within a space, back to previous locations, and forward to locations that have been navigated back from.
 *
 * @param K The type of the keys that identify locations in the space.
 * @param A The type of the arguments that can be passed when navigating to a location.
 */
interface StackNavigationController<K, A> {

    /**
     * The space that the controller is navigating within.
     */
    val space: Space<K>

    /**
     * Navigate to a new location in the space, passing the given arguments.
     */
    fun navigateTo(location: @UnsafeVariance K, arguments: A?)

    /**
     * The current location in the space.
     */
    val currentLocation: StateFlow<K>

    /**
     * A list of locations that the user has navigated through.
     */
    val backList: StateFlow<List<K>>

    /**
     * Go back to the previous location in the [backList].
     */
    fun goBack()

    /**
     * A list of locations that the user has navigated back from. This gets reset when the user navigates forward to a new location.
     */
    val forwardList: StateFlow<List<K>>
    /**
     * Go forward to the next location in [forwardList].
     */
    fun goForward()

    data class Frame<K, A>(
        val location: K,
        val arguments: A?,
        // options
    )

    val currentFrame: StateFlow<Frame<K, A>>

}

class StackNavigationControllerImpl<K: Any?, A: Any?>(
    initialLocation: K,
    val stateScope: CoroutineScope = CoroutineScope(Dispatchers.Main),
): StackNavigationController<K, A> {

    val _currentFrame = MutableStateFlow(StackNavigationController.Frame(initialLocation, null))

    private val _space = AggregateSpace<K>()
    override val space: Space<K>
        get() = _space

    override val currentLocation: StateFlow<K>
        get() = _currentFrame.value.location.let { currentValue ->
            _currentFrame.map { it.location }
                .stateIn(stateScope, SharingStarted.Eagerly, currentValue)
        }

    override val backList: StateFlow<List<K>>
        get() = TODO("Not yet implemented")

    override fun goBack() {
        TODO("Not Yet Implemented")
    }

    override val forwardList: StateFlow<List<K>>
        get() = TODO("Not yet implemented")

    override fun goForward() {

    }

    override val currentFrame: StateFlow<StackNavigationController.Frame<K, A>>
        get() = TODO("Not yet implemented")

    override fun navigateTo(location: K, arguments: A?) {
        TODO()
    }
}

fun <K, A> StackNavigationController(
    initialLocation: K,
): StackNavigationController<K, A> {
    return StackNavigationControllerImpl(initialLocation)
}
