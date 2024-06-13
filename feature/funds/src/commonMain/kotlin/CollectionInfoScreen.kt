package org.pointyware.painteddogs.feature.collections.core

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class FundInfoScreenState(
    val title: String,
)

/**
 *
 * TODO: replace/move to :feature:collections module; rename FundInfoScreen
 */
@Composable
fun FundInfoScreen(
    state: FundInfoScreenState,
    onContribute: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = state.title
        )
        Button(
            onClick = { onContribute() },
        ) {
            Text(
                text = "Make Contribution"
            )
        }
    }
}
