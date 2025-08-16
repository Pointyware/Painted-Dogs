package org.pointyware.painteddogs.feature.funds.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.entities.Uuid

/**
 * UI state for CollectionDetailView
 */
data class FundDetailsViewState(
    val title: String,
    val description: String,
    val dirty: Boolean = false
)

/**
 *
 */
@Composable
fun FundDetailsView(
    state: FundDetailsViewState,
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text("Title: ${state.title}")
        Text("Description: ${state.description}")

        Button(
            onClick = onConfirm
        ) {
            Text(text = "Submit")
        }
    }
}
