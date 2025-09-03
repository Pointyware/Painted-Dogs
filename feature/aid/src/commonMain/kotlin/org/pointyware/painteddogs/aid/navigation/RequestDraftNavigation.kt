package org.pointyware.painteddogs.aid.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.ui.RequestScreen
import org.pointyware.painteddogs.aid.ui.RequestScreenState
import org.pointyware.painteddogs.aid.viewmodels.RequestViewModel
import org.pointyware.painteddogs.core.ui.composeKoinViewModel


/**
 * The Screen where a user can request an offer for the mutual aid network.
 */
@Serializable
data class DraftRequestDestination(
    val resource: Resource
): AidDestination

/**
 *
 */
fun NavController.navigateToRequestDraft(resource: Resource) {
    navigate(DraftRequestDestination(resource)) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.findStartDestination().id)
    }
}

fun NavGraphBuilder.requestDraftDestination(
    onNavigateToRequest: (ResourceRequest)->Unit,
) {

    composable<DraftRequestDestination> {
        val viewModel: RequestViewModel = composeKoinViewModel()
        val state by viewModel.state.collectAsState()
        val error by viewModel.error.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.onRequestCreated.collect {
                onNavigateToRequest(it)
            }
        }
        RequestScreen(
            state = state.let {
                RequestScreenState(
                    temporalScope = it.temporalScope,
                    description = it.description,
                )
            },
            throwable = error,
            onScopeSelected = viewModel::onTemporalScopeSelected,
            onChangeDescription = viewModel::onDescriptionChanged,
            onSubmit = viewModel::onSubmit,
            onClearError = viewModel::onClearError,
        )
    }
}
