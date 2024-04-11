package org.pointyware.painteddogs.core.ui

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
import kotlinx.datetime.Instant

data class DateSelectorRowState(
    val date: Instant?,
)

/**
 * A row that allows a user to select or change a date or clear the selection.
 * When a date is selected, the date is displayed in the row, with a button to clear the selection.
 * The user can also click on the date to change it.
 * When no date is selected, a button to select a date is displayed.
 */
@OptIn(ExperimentalMaterial3Api::class)
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
            Text(
                text = localDateTime.toString(),
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
