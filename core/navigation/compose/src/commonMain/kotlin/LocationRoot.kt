package org.pointyware.painteddogs.core.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.navigation.StackNavigationController

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
