package org.pointyware.painteddogs.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import kotlin.reflect.KClass

/**
 * Routes a user to content for a given instance's type.
 */
@Composable
fun <K: Any, V> TypeRouter(
    navController: StackNavigationController<K, V>,

    modifier: Modifier = Modifier,
    content: @Composable TypeRouterScope<K, V>.() -> Unit,
) {
    // TODO: remove navigation callbacks in "routing" functions when type-safe navigation is implemented
    val typeRouterScope = TypeRouterScopeImpl<K, V>()
    typeRouterScope.content()

    val currentLocation by navController.currentLocation.collectAsState()
    Box(modifier = modifier) {
        val locationArgument = currentLocation ?: Unit
        val currentType = locationArgument::class
        val currentContent = typeRouterScope.locations[currentType]
        @Suppress("UNCHECKED_CAST")
        currentContent?.invoke(navController, locationArgument as? K) ?: throw IllegalArgumentException("No content for location $currentLocation")
    }
}

interface TypeRouterScope<K: Any, V> {
    val locations: Map<KClass<K>, @Composable (StackNavigationController<K, V>, K?) -> Unit>

    fun location(type: KClass<K>, content: @Composable (StackNavigationController<K, V>, K?) -> Unit)
}

class TypeRouterScopeImpl<K: Any, V>(): TypeRouterScope<K, V> {
    private val _locations = mutableMapOf<KClass<K>, @Composable (StackNavigationController<K, V>, K?) -> Unit>()
    override val locations: Map<KClass<K>, (StackNavigationController<K, V>, K?) -> Unit>
        get() = _locations

    override fun location(
        type: KClass<K>,
        content: @Composable (StackNavigationController<K, V>, K?) -> Unit
    ) {
        _locations[type] = content
    }
}
