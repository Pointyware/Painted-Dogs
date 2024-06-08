package org.pointyware.painteddogs

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
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

    val appDependencies = remember { getDependencies() }

    val state = rememberWindowState()
    Window(
        title = "My Application",
        state = state,
        onCloseRequest = this::exitApplication
    ) {
        PaintedDogsApp(
            dependencies = appDependencies,
            isDarkTheme = false // TODO: Create desktop implementation of theme
        )
    }
}
