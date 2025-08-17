package org.pointyware.painteddogs.feature.funds.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class FundHistoryScreenState(
    val collections: List<FundRowState> = emptyList()
)
/**
 * TODO: replace/move to :feature:collections module; rename FundHistoryScreen
 */
@OptIn(ExperimentalUuidApi::class)
@Composable
fun FundHistoryScreen(
    state: FundHistoryScreenState,
    modifier: Modifier = Modifier,
    onViewFund: (Uuid) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        LazyColumn {
            items(state.collections) {
                 FundRow(
                     state = it,
                     onTap = { fundId -> onViewFund(fundId) }
                 )
            }
        }
    }
}
