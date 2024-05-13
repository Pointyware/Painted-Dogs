package org.pointyware.painteddogs

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.datetime.Clock
import org.pointyware.painteddogs.core.ui.DateSelectorRow
import org.pointyware.painteddogs.core.ui.DateSelectorRowState

fun main() = application {

    val state = rememberWindowState()
    Window(
        title = "My Application",
        state = state,
        onCloseRequest = this::exitApplication
    ) {
        DateSelectorRow(
            state = DateSelectorRowState(
                date = Clock.System.now()
            ),
            onDateChanged = { println("Date Changed") }
        )
    }
}
