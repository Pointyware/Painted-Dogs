package org.pointyware.painteddogs.feature.collections.core

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.core.entities.CurrencyAmount

data class MakeContributionViewState(
    val fundId: String,
    val contributionAmount: CurrencyAmount,
)

/**
 *
 */
@Composable
fun MakeContributionView(
    state: MakeContributionViewState,
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Contribute to Fund ${state.fundId}"
        )
        Text(
            text = "Amount: ${state.contributionAmount}"
        )

        Button(onClick = onConfirm) {
            // button text
            Text("Submit")
        }
    }
}
