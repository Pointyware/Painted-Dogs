package org.pointyware.painteddogs.shared.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.funds.ui.FundRow
import org.pointyware.painteddogs.feature.funds.ui.FundRowState

data class HomeScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val funds: List<FundRowState> = emptyList(),
)

/**
 * Main view shown when there is no active navigation.
 */
@Composable
fun HomeScreen(
    state: HomeScreenState,
    modifier: Modifier = Modifier,
    onFundSelected: (Uuid) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(state.funds) {
                FundRow(
                    state = it,
                    onTap = { fundId -> onFundSelected(fundId) },
                )
            }
        }
    }
}
