package org.pointyware.painteddogs.core.ui.views

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
) {
    Button(onClick = { println("Click") }) {
        // button text
        Text("Submit")
    }
}
