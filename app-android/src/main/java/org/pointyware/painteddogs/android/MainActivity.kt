package org.pointyware.painteddogs.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import org.koin.core.context.startKoin
import org.pointyware.painteddogs.app.PaintedDogsApp
import org.pointyware.painteddogs.app.di.appModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            modules(appModule())
        }
        setContent {
            PaintedDogsApp(
                isDarkTheme = isSystemInDarkTheme()
            )
        }
    }
}
