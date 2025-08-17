package org.pointyware.painteddogs.feature.funds.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class FundInfoScreenState(
    val id: Uuid,
    val title: String,
)

/**
 *
 */
@OptIn(ExperimentalUuidApi::class)
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
