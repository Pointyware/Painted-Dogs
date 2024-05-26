package org.pointyware.painteddogs.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.ui.views.FundRow
import org.pointyware.painteddogs.core.ui.views.FundRowState

data class FundHistoryScreenState(
    val contributions: List<FundRowState> = emptyList(),
)

/**
 * Lists a user's contribution history, allowing them to view details about each fund.
 */
@Composable
fun FundHistoryScreen(
    state: FundHistoryScreenState,
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
