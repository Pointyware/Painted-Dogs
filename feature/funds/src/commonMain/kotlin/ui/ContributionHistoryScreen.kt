package org.pointyware.painteddogs.feature.collections.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.feature.collections.core.FundRow
import org.pointyware.painteddogs.feature.collections.core.FundRowState
import org.pointyware.painteddogs.feature.collections.core.viewmodels.ContributionHistoryViewModel

data class ContributionHistoryScreenState(
    val contributions: List<FundRowState>,
)

/**
 * Lists a user's contribution history, allowing them to view details about each fund.
 */
@Composable
fun ContributionHistoryScreen(
    state: ContributionHistoryScreenState,
    modifier: Modifier = Modifier,
    onViewFund: (String) -> Unit
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

@Composable
fun ContributionHistoryScreen(
    viewModel: ContributionHistoryViewModel,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsState()
    ContributionHistoryScreen(
        state = ContributionHistoryMapper.map(state.value),
        modifier = modifier,
        onViewFund = viewModel::onViewFund
    )
}
