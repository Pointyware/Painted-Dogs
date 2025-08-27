package org.pointyware.painteddogs.aid.ui

import androidx.compose.material3.Button
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.aid.entities.Resource
import selectedColors
import unSelectedColors

@Composable
fun ResourceSelector(
    value: Resource,
    onResourceSelected: (Resource) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier
    ) {
        listOf(
            Resource.Food,
            Resource.Housing,
            Resource.Funds,
            Resource.Skills,
            Resource.Protection
        ).forEach { resource ->
            Button(
                onClick = { onResourceSelected(resource) },
                colors = if (value == resource) selectedColors() else unSelectedColors(),
            ) {
                Text(
                    text = stringForResource(resource)
                )
            }
        }
    }
}
