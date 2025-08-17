package org.pointyware.painteddogs.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.pointyware.painteddogs.core.ui.PopularDevicePreviews
import org.pointyware.painteddogs.core.ui.design.LocalDateFormat
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class DateSelectorRowState(
    val date: Instant?,
)

/**
 * A row that allows a user to select or change a date or clear the selection.
 * When a date is selected, the date is displayed in the row, with a button to clear the selection.
 * The user can also click on the date to change it.
 * When no date is selected, a button to select a date is displayed.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun DateSelectorRow(
    state: DateSelectorRowState,
    modifier: Modifier = Modifier,
    onDateChanged: (Instant?) -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        var datePickerOpen by remember { mutableStateOf(false) }
        // Date
        state.date?.let { localDateTime ->
            val formattedDate = LocalDateFormat.current.format(localDateTime)
            Text(
                text = formattedDate,
                modifier = Modifier.clickable {
                    datePickerOpen = true
                }
            )
        } ?: run {
            Button(onClick = { datePickerOpen = true }) {
                Text(text = "Select Date")
            }
        }

        // Clear button
        Button(onClick = { onDateChanged(null) }) {
            Text(text = "Clear")
        }

        if (datePickerOpen) {
            val pickerState = rememberDatePickerState(
                initialSelectedDateMillis = state.date?.epochSeconds
            )

            DatePickerDialog(
                onDismissRequest = { datePickerOpen = false },
                dismissButton = { datePickerOpen = false },
                confirmButton = {
                    val selectedDateMillis = pickerState.selectedDateMillis ?: return@DatePickerDialog
                    onDateChanged(Instant.fromEpochMilliseconds(selectedDateMillis))
                }
            ) {
                DatePicker(
                    state = pickerState,
                )
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun DateSelectorRowPreview() {
    var state by remember { mutableStateOf(DateSelectorRowState(date = Clock.System.now()))}
    val onDateChanged: (Instant?) -> Unit = { state = state.copy(date = it) }

    PopularDevicePreviews {
        DateSelectorRow(
            state = state,
            onDateChanged = onDateChanged
        )
    }
}
