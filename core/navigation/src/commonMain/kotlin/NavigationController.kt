package org.pointyware.painteddogs.core.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlin.reflect.KClass


/**
 * Gives the ability to navigate amongst locations within a space, back to previous locations, and forward to locations that have been navigated back from.
 */
interface StackNavigationController {

    /**
     * Navigate to a new location in the space, passing the given arguments.
     */
    fun <K:Any> navigateTo(route: KClass<K>, arguments: K? = null)

    /**
     * The current location in the space.
     */
    val currentLocation: StateFlow<KClass<*>>

    /**
     * A list of locations that the user has navigated through.
     */
    val backList: StateFlow<List<KClass<*>>>

    /**
     * Go back to the previous location in the [backList].
     */
    fun goBack()

    /**
     * A list of locations that the user has navigated back from. This gets reset when the user navigates forward to a new location.
     */
    val forwardList: StateFlow<List<KClass<*>>>
    /**
     * Go forward to the next location in [forwardList].
     */
    fun goForward()

    data class Frame<K:Any>(
        val location: KClass<K>,
        val arguments: K?,
        // options
    )

    val currentFrame: StateFlow<Frame<*>>

}

class StackNavigationControllerImpl(
    private val initialLocation: KClass<*>,
    private val stateScope: CoroutineScope = CoroutineScope(Dispatchers.Main),
): StackNavigationController {

    internal data class State(
        val backList: List<StackNavigationController.Frame<*>>,
        val forwardList: List<StackNavigationController.Frame<*>>,
        val frame: StackNavigationController.Frame<*>,
    )
    private val mutableState = MutableStateFlow(State(emptyList(), emptyList(), StackNavigationController.Frame(initialLocation, null)))

    override val currentLocation: StateFlow<KClass<*>>
        get() = mutableState.value.frame.location.let { currentValue ->
            mutableState.map { it.frame.location }
                .stateIn(stateScope, SharingStarted.WhileSubscribed(), currentValue)
        }


    override val backList: StateFlow<List<KClass<*>>>
        get() = mutableState.value.let { currentValue ->
            mutableState.map { it.backList.map { it.location } }
                .stateIn(stateScope, SharingStarted.WhileSubscribed(), currentValue.backList.map { it.location })
        }

    override fun goBack() {
        mutableState.update { currentState ->
            val previous = currentState.backList.lastOrNull()
            previous?.let {
                currentState.copy(
                    backList = currentState.backList.dropLast(1),
                    forwardList = currentState.forwardList + currentState.frame,
                    frame = previous
                )
            } ?: throw IllegalStateException("Back stack is empty")
        }
    }

    override val forwardList: StateFlow<List<KClass<*>>>
        get() = mutableState.value.let { currentValue ->
            mutableState.map { it.forwardList.map { it.location } }
                .stateIn(stateScope, SharingStarted.WhileSubscribed(), currentValue.forwardList.map { it.location })
        }

    override fun goForward() {
        mutableState.update { currentState ->
            val next = currentState.forwardList.lastOrNull()
            next?.let {
                currentState.copy(
                    backList = currentState.backList + currentState.frame,
                    forwardList = currentState.forwardList.dropLast(1),
                    frame = next
                )
            } ?: throw IllegalStateException("Back stack is empty")
        }
    }

    override val currentFrame: StateFlow<StackNavigationController.Frame<*>>
        get() = mutableState.value.frame.let { currentValue ->
            mutableState.map { it.frame }
                .stateIn(stateScope, SharingStarted.WhileSubscribed(), currentValue)
        }

    override fun <K:Any> navigateTo(route: KClass<K>, arguments: K?) {
        mutableState.update {
            it.copy(
                backList = it.backList + it.frame,
                forwardList = emptyList(),
                frame = StackNavigationController.Frame(route, arguments),
            )
        }
    }
}

fun StackNavigationController(
    initialLocation: KClass<*>,
): StackNavigationController {
    return StackNavigationControllerImpl(initialLocation)
}
