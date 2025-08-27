package org.pointyware.painteddogs.aid

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.pointyware.painteddogs.aid.ui.AidBoardView
import org.pointyware.painteddogs.aid.ui.AidBoardViewModel
import org.pointyware.painteddogs.aid.viewmodels.MutualAidViewModel

@Composable
fun MutualAidScreen(
    viewModel: MutualAidViewModel,
    navController: NavController
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navEvent.collect { destination ->
            navController.navigate(destination)
        }
    }
    val viewState = remember(state) { AidBoardViewModel(state) }
    AidBoardView(
        state = viewState,
        onOfferClaim = viewModel::onOfferClaim,
        onRequestResponse = viewModel::onRequestResponse,
        modifier = Modifier.fillMaxSize()
    )
}
