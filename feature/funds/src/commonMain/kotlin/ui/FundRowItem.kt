package org.pointyware.painteddogs.feature.funds.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class FundRowState(
    val fundId: String,
    val title: String,
    val description: String,
)

/**
 * Displays basic information about a fund in a row format.
 */
@Composable
fun FundRow(
    state: FundRowState,
    modifier: Modifier = Modifier,
    onTap: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .clickable { onTap(state.fundId) }
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = state.description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
    }
}
