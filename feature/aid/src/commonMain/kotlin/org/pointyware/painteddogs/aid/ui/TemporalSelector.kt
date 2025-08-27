package org.pointyware.painteddogs.aid.ui

import androidx.compose.material3.Button
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.aid.entities.TemporalScope
import selectedColors
import unSelectedColors

@Composable
fun TemporalSelector(
    value: TemporalScope,
    onScopeSelected: (TemporalScope)->Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier
    ) {
        listOf(
            TemporalScope.Indefinite,
            TemporalScope.Limited,
            TemporalScope.Schedule,
            TemporalScope.Event,
            TemporalScope.Immediate
        ).forEach { scope ->
            Button(
                onClick = { onScopeSelected(scope) },
                colors = if (value == scope) selectedColors() else unSelectedColors(),
            ) {
                Text(
                    text = stringForScope(scope)
                )
            }
        }
    }
}
