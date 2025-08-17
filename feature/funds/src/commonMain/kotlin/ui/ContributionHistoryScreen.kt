package org.pointyware.painteddogs.feature.funds.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.feature.funds.viewmodels.ContributionHistoryViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class ContributionHistoryScreenState(
    val contributions: List<FundRowState>,
)

/**
 * Lists a user's contribution history, allowing them to view details about each fund.
 */
@OptIn(ExperimentalUuidApi::class)
@Composable
fun ContributionHistoryScreen(
    state: ContributionHistoryScreenState,
    modifier: Modifier = Modifier,
    onViewFund: (Uuid) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        LazyColumn {
            items(state.contributions) {
                FundRow(state = it, onTap = { fundId -> onViewFund(fundId) })
            }
        }
    }
}

@OptIn(ExperimentalUuidApi::class)
@Composable
fun ContributionHistoryScreen(
    viewModel: ContributionHistoryViewModel,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsState()
    ContributionHistoryScreen(
        state = ContributionHistoryUiStateMapper.map(state.value),
        modifier = modifier,
        onViewFund = viewModel::onViewFund
    )
}
