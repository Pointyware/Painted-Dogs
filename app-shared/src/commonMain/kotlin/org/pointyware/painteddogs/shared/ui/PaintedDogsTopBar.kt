package org.pointyware.painteddogs.shared.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.shared.Res
import org.pointyware.painteddogs.shared.app_name
import org.pointyware.painteddogs.shared.home.Home

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaintedDogsTopBar(
    navController: NavController
) {
    val currentLocation = navController.currentBackStackEntryAsState()
    CenterAlignedTopAppBar(
        modifier = Modifier.shadow(elevation = GeometryTokens.dpMedium),
//                    colors = TopAppBarColors(
//                        containerColor =
//                        navigationIconContentColor =
//                        titleContentColor =
//                        actionIconContentColor =
//                        scrolledContainerColor =
//                    ),
        navigationIcon = {
            val stack = navController.currentBackStack.collectAsState()
            if (stack.value.isNotEmpty()) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Profile")
                }
            }
        },
        title = {
            val location = currentLocation.value
            val titleRes = when(location) {
                Home -> Res.string.app_name
                else -> null
            }
            Text(titleRes?.let { stringResource(it) } ?: "Generated: $location")
        },
        actions = {

        },
    )
}
