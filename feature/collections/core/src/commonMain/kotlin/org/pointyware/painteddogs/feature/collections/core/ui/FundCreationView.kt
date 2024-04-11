package org.pointyware.painteddogs.feature.collections.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant
import org.pointyware.painteddogs.core.ui.DateSelectorRow
import org.pointyware.painteddogs.core.ui.DateSelectorRowState

data class FundCreationViewState(
    val title: String,
    val description: String,
    val targetAmount: String,
    val startDate: String,
    val endDate: String,
)


@Composable
fun rememberFundCreationViewState(
    title: String = "",
    description: String = "",
    targetAmount: String = "",
    startDate: String = "",
    endDate: String = "",
) = remember(title) {
    FundCreationViewState(
        title = title,
        description = description,
        targetAmount = targetAmount,
        startDate = startDate,
        endDate = endDate,
    )
}


/**
 *
 */
@Composable
fun FundCreationView(
    state: FundCreationViewState = rememberFundCreationViewState(),
    modifier: Modifier = Modifier,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onGoalChange: (Double) -> Unit,
    onStartDateChanged: (Instant?) -> Unit,
    onEndDateChanged: (Instant?) -> Unit,
    onSubmitDonation: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        // Title
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Create Donation",
                fontSize = MaterialTheme.typography.labelLarge.fontSize,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
        }

        // Description & Media
        Column(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = state.title,
                label = { Text("Title") },
                onValueChange = { onTitleChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            TextField(
                value = state.description,
                label = { Text("Description") },
                onValueChange = { onDescriptionChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            // ... other media upload components
        }

        // Goal
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Goal",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 4.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
            ) {
                TextField(
                    value = state.targetAmount.toString(),
                    onValueChange = { onGoalChange(it.toDouble()) },
                    modifier = Modifier
                        .padding(end = 16.dp)
                )
            }
        }

        // Dates
        Column(modifier = Modifier.fillMaxWidth()) {
            DateSelectorRow(
                state = DateSelectorRowState(null),
                onDateChanged = onStartDateChanged
            )
            DateSelectorRow(
                state = DateSelectorRowState(null),
                onDateChanged = onEndDateChanged
            )
        }

        // Button
        Button(
            onClick = onSubmitDonation,
            modifier = Modifier.fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            Text(text = "Create Donation")
        }
    }
}
