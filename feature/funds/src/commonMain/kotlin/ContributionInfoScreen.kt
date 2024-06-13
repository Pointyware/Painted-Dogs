package org.pointyware.painteddogs.feature.funds

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class ContributionInfoScreenState(
    val id: String,
    val title: String,
    val description: String,
)

/**
 * Displays information about a contribution already made to a collection.
 */
@Composable
fun ContributionInfoScreen(
    state: ContributionInfoScreenState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Contribution Details",
            style = MaterialTheme.typography.displaySmall,
        )
        Text(
            text = state.title,
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            text = state.description,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}
