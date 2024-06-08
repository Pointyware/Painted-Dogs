package org.pointyware.painteddogs

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.pointyware.painteddogs.app.PaintedDogsApp
import org.pointyware.painteddogs.app.di.appModule
import org.pointyware.painteddogs.app.di.getDependencies

fun main() = application {

    startKoin {
        modules(
            appModule()
        )
    }

    val appDependencies = runBlocking { getDependencies() }

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
