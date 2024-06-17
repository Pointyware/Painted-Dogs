package org.pointyware.painteddogs.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Routes a user to content for a given string list.
 */
@Composable
fun <V> StringListRouter(
    navController: StackNavigationController<Route<String>, V>,

    modifier: Modifier = Modifier,
    content: @Composable LocationRootScope<Route<String>, V>.() -> Unit,
) {

}
