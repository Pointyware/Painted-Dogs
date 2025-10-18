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
import org.pointyware.painteddogs.aid.ui.RequestScreen
import org.pointyware.painteddogs.aid.ui.RequestScreenState
import org.pointyware.painteddogs.aid.viewmodels.RequestDraftViewModel
import org.pointyware.painteddogs.core.ui.composeKoinViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


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

@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.requestDraftDestination(
    onNavigateToRequest: (Uuid)->Unit,
) {

    composable<DraftRequestDestination> {
        val viewModel: RequestDraftViewModel = composeKoinViewModel()
        val state by viewModel.state.collectAsState()
        val error by viewModel.error.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.requestCreationFlow.collect {
                onNavigateToRequest(it)
            }
        }
        RequestScreen(
            state = state.let {
                RequestScreenState(
                    temporalScope = it.model.scope,
                    description = it.model.description,
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
