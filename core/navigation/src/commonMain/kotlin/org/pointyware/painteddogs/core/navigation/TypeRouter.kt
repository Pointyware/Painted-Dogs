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
fun TypeRouter(
    navController: StackNavigationController,

    modifier: Modifier = Modifier,
    content: @Composable TypeRouterScope.() -> Unit,
) {
    // TODO: remove navigation callbacks in "routing" functions when type-safe navigation is implemented
    val typeRouterScope = TypeRouterScopeImpl()
    typeRouterScope.content()

    val currentLocation by navController.currentLocation.collectAsState()
    Box(modifier = modifier) {
        val locationArgument = currentLocation
        val currentType = locationArgument::class
        val currentContent = typeRouterScope.locations[currentType]
        @Suppress("UNCHECKED_CAST")
        currentContent?.invoke(navController, locationArgument) ?: throw IllegalArgumentException("No content for location $currentLocation")
    }
}

interface TypeRouterScope {
    val locations: Map<KClass<*>, @Composable (StackNavigationController, Any?) -> Unit>

    fun <K:Any> location(type: KClass<K>, content: @Composable (StackNavigationController, K?) -> Unit)
}

class TypeRouterScopeImpl(): TypeRouterScope {
    private val _locations = mutableMapOf<KClass<*>, @Composable (StackNavigationController, Any?) -> Unit>()
    override val locations: Map<KClass<*>, @Composable (StackNavigationController, Any?) -> Unit>
        get() = _locations

    override fun <K:Any> location(
        type: KClass<K>,
        content: @Composable (StackNavigationController, K?) -> Unit
    ) {
        @Suppress("UNCHECKED_CAST")
        _locations[type] = content as @Composable (StackNavigationController, Any?) -> Unit
    }
}
