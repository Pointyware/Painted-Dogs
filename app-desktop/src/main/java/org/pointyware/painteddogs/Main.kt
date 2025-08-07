package org.pointyware.painteddogs

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.pointyware.painteddogs.shared.PaintedDogsApp
import org.pointyware.painteddogs.ui.theme.PaintedDogsTheme

fun main() = application {

    val state = rememberWindowState()
    Window(
        title = "My Application",
        state = state,
        onCloseRequest = this::exitApplication
    ) {
        PaintedDogsTheme {
            PaintedDogsApp()
        }
    }
}
