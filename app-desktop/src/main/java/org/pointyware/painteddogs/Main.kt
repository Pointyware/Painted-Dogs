package org.pointyware.painteddogs

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.datetime.Clock
import org.pointyware.painteddogs.core.ui.components.DateSelectorRow
import org.pointyware.painteddogs.core.ui.components.DateSelectorRowState
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import org.pointyware.painteddogs.core.ui.design.PopularDevicePreviews

fun main() = application {

    val state = rememberWindowState()
    Window(
        title = "My Application",
        state = state,
        onCloseRequest = this::exitApplication
    ) {
        PopularDevicePreviews {
            PaintedDogsTheme {
                DateSelectorRow(
                    state = DateSelectorRowState(
                        date = Clock.System.now()
                    ),
                    onDateChanged = { println("Date Changed") }
                )
            }
        }
        /*
        TODO: Create navigation MVP
          1. Create Home Screen (with a button to create a collection)
          2.
         */
    }
}
