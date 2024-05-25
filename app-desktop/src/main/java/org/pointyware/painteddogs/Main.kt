package org.pointyware.painteddogs

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.pointyware.painteddogs.app.PaintedDogsApp
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme

fun main() = application {


    
    val state = rememberWindowState()
    Window(
        title = "My Application",
        state = state,
        onCloseRequest = this::exitApplication
    ) {
        PaintedDogsApp()
    }
}
