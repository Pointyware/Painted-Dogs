import androidx.compose.runtime.remember
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.core.context.startKoin
import org.pointyware.painteddogs.app.PaintedDogsApp
import org.pointyware.painteddogs.app.di.appModule
import org.pointyware.painteddogs.app.di.getDependencies
import painted_dogs.app_desktop.generated.resources.Res
import painted_dogs.app_desktop.generated.resources.tray_icon

@OptIn(ExperimentalResourceApi::class)
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
            isDarkTheme = false
        )
    }

    val painter = painterResource(Res.drawable.tray_icon)
    Tray(
        icon = painter,
        menu = {
            Menu("File") {
            }
        }
    )
}
