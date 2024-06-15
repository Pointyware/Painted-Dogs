package org.pointyware.painteddogs.feature.funds.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.entities.Uuid

data class FundInfoScreenState(
    val id: Uuid,
    val title: String,
)

/**
 *
 */
@Composable
fun FundInfoScreen(
    state: FundInfoScreenState,
    onContribute: (Uuid) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = state.title
        )
        Button(
            onClick = { onContribute(state.id) },
        ) {
            Text(
                text = "Make Contribution"
            )
        }
    }
}
