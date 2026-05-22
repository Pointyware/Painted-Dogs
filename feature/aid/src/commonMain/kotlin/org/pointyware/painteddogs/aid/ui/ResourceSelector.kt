package org.pointyware.painteddogs.aid.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.pointyware.painteddogs.aid.entities.Resource

@Composable
fun SingleResourceSelector(
    value: Resource,
    onResourceSelected: (Resource) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier
    ) {
        val entries = Resource.entries
        entries.forEachIndexed { index, resource ->
            SegmentedButton(
                selected = resource == value,
                onClick = { onResourceSelected(resource) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = entries.size),
            ) {
                Text(
                    text = stringForResource(resource),
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun MultiResourceSelector(
    values: Set<Resource>,
    onSelectionChanged: (Set<Resource>)->Unit,
    modifier: Modifier = Modifier
) {
    MultiChoiceSegmentedButtonRow(
        modifier = modifier
    ) {
        val entries = Resource.entries
        entries.forEachIndexed { index, resource ->
            SegmentedButton(
                checked = resource in values,
                onCheckedChange = { onSelectionChanged(values + resource) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = entries.size),
            ) {
                Text(
                    style = MaterialTheme.typography.labelSmall,
                    text = stringForResource(resource),
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
