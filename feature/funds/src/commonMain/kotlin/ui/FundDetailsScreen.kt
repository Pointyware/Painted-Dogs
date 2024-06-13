package org.pointyware.painteddogs.feature.funds.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


/**
 * Displays options for creating a new collection.
 * TODO: replace/move to :feature:collections module; rename FundDetailsScreen
 */
@Composable
fun FundDetailsScreen(
    state: FundDetailsViewState,
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit
) {
    FundDetailsView(
        state = state,
        modifier = modifier,
        onConfirm = onConfirm
    )
}
