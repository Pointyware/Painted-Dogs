package org.pointyware.painteddogs.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 * A composable that provides a location-based navigation root.
 */
@Composable
fun <K, V> LocationRoot(
    navController: StackNavigationController<K, V>,

    modifier: Modifier = Modifier,
    content: LocationRootScope<K>.() -> Unit,
) {
    val locationRootScope = remember(navController, content) {
        LocationRootScopeImpl(navController).also {
            it.content()
        }
    }

    val currentLocation by navController.currentLocation.collectAsState()
    Box(modifier = modifier) {
        locationRootScope.locations[currentLocation]?.invoke()
    }
}

interface LocationRootScope<K> {
    fun location(key: K, content: @Composable () -> Unit)
}

private class LocationRootScopeImpl<K, V>(
    private val navController: StackNavigationController<K, V>,
) : LocationRootScope<K> {

    val locations = mutableMapOf<K, @Composable () -> Unit>()
    override fun location(key: K, content: @Composable () -> Unit) {
        locations[key] = content
    }
}
