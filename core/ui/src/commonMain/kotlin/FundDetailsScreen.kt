package org.pointyware.painteddogs.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class FundDetailsScreenState(
    val title: String,
    val description: String,
    val dirty: Boolean = false
)

/**
 * Displays options for creating a new collection.
 * TODO: replace/move to :feature:collections module; rename FundDetailsScreen
 */
@Composable
fun FundDetailsScreen(
    state: FundDetailsScreenState,
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit
) {
    Column {
        Text("Title: ${state.title}")
        Text("Description: ${state.description}")
    }
}
