package org.pointyware.painteddogs.desktop

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.compose.resources.painterResource
import org.koin.core.context.startKoin
import org.pointyware.painteddogs.desktop.di.desktopModule
import org.pointyware.painteddogs.shared.PaintedDogsApp
import org.pointyware.painteddogs.shared.di.appModule
import org.pointyware.painteddogs.shared.di.getDependencies

fun main() = application {

    startKoin {
        modules(
            desktopModule(),
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
            isDarkTheme = false
        )
    }

    Tray(
        icon = painterResource(Res.drawable.tray_icon),
        menu = {
            Menu("File") {
            }
        }
    )
}
