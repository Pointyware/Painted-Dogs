package org.pointyware.painteddogs.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.ui.views.FundDetailsView
import org.pointyware.painteddogs.core.ui.views.FundDetailsViewState


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
