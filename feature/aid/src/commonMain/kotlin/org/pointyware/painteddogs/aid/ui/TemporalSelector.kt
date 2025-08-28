package org.pointyware.painteddogs.aid.ui

import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.pointyware.painteddogs.aid.entities.TemporalScope

@Composable
fun SingleTemporalSelector(
    value: TemporalScope,
    onScopeSelected: (TemporalScope)->Unit,
    modifier: Modifier = Modifier,
    options: List<TemporalScope> = TemporalScope.entries
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier
    ) {
        options.forEachIndexed { index, scope ->
            SegmentedButton(
                selected = scope == value,
                onClick = { onScopeSelected(scope) },
                shape = SegmentedButtonDefaults.itemShape(index, options.size),
            ) {
                Text(
                    text = stringForScope(scope),
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun MultiTemporalSelector(
    values: Set<TemporalScope>,
    onScopeSelected: (TemporalScope)->Unit,
    modifier: Modifier = Modifier,
    options: List<TemporalScope> = TemporalScope.entries
) {
    MultiChoiceSegmentedButtonRow(
        modifier = modifier
    ) {
        options.forEachIndexed { index, scope ->
            SegmentedButton(
                checked = scope in values,
                onCheckedChange = { onScopeSelected(scope) },
                shape = SegmentedButtonDefaults.itemShape(index, options.size),
            ) {
                Text(
                    text = stringForScope(scope),
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
