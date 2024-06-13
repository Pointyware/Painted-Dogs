package org.pointyware.painteddogs.feature.collections.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * UI state for CollectionDetailView
 */
data class FundDetailsViewState(
    val title: String,
    val description: String,
    val dirty: Boolean = false
)

/**
 * TODO: replace/move to :feature:collections module
 */
@Composable
fun FundDetailsView(
    state: FundDetailsViewState,
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit
) {
    Column {
        Text("Title: ${state.title}")
        Text("Description: ${state.description}")
    }
}
