package org.pointyware.painteddogs.feature.funds

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.entities.Uuid

data class ContributionDetailsScreenState(
    val id: Uuid,
    val title: String,
    val description: String,
)

/**
 * Displays options for making a new contribution to an existing collection.
 */
@Composable
fun ContributionDetailsScreen(
    state: ContributionDetailsScreenState,
    modifier: Modifier = Modifier,
    onConfirm: (Uuid) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Contribution Details"
        )
        Text(
            text = state.title
        )
        Text(
            text = state.description
        )
        Text(
            text = "Contribution ID: ${state.id}"
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onConfirm(state.id) },
        ) {
            Text(
                text = "Confirm Contribution"
            )
        }
    }
}
