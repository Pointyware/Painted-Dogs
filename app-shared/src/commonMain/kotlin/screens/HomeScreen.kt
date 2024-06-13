package org.pointyware.painteddogs.shared.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.feature.collections.core.FundRow
import org.pointyware.painteddogs.feature.collections.core.FundRowState

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
    onCreateFund: () -> Unit,
    onSearchFunds: () -> Unit,
    onViewProfile: () -> Unit,
    onFundSelected: (String) -> Unit,
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
        Button(
            onClick = onCreateFund
        ) {
            Text(text = "Create New Collection")
        }
        Button(
            onClick = onSearchFunds
        ) {
            Text(text = "Search Collections")
        }
        Button(
            onClick = onViewProfile
        ) {
            Text(text = "View Profile")
        }
    }
}
