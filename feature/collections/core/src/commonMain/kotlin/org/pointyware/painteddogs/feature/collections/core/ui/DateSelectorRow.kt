package org.pointyware.painteddogs.feature.collections.core.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
data class DateSelectorRowState(
    val isStartDateSelected: Boolean,
    val title: String,
    val datePickerState: DatePickerState,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDateSelectorRowState(
    isStartDateSelected: Boolean = false,
    title: String = "",
    datePickerState: DatePickerState = rememberDatePickerState(),
) = remember(title) {
    DateSelectorRowState(
        isStartDateSelected = isStartDateSelected,
        title = title,
        datePickerState = datePickerState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelectorRow(
    state: DateSelectorRowState = rememberDateSelectorRowState(),
    modifier: Modifier = Modifier,
    onStartDateSelectedChange: (Boolean) -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Checkbox(
            checked = state.isStartDateSelected,
            onCheckedChange = { onStartDateSelectedChange(it) },
        )
        Text(
            text = state.title,
        )
        DatePicker(
            state = state.datePickerState,
        )
    }
}
