package org.pointyware.painteddogs

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.pointyware.painteddogs.app.PaintedDogsApp

fun main() = application {



    val state = rememberWindowState()
    Window(
        title = "My Application",
        state = state,
        onCloseRequest = this::exitApplication
    ) {
        PaintedDogsApp(
            isDarkTheme = false // TODO: Create desktop implementation of theme
        )
    }
}
