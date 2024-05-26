package org.pointyware.painteddogs.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A composable that provides a location-based navigation root.
 */
@Composable
fun <K, V> LocationRoot(
    navController: StackNavigationController<K, V>,
    startLocation: K,

    modifier: Modifier = Modifier,
    content: @Composable LocationRootScope<K>.() -> Unit,
) {

}

interface LocationRootScope<K> {
    fun location(key: K, content: @Composable () -> Unit)
}
