package org.pointyware.painteddogs.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import org.pointyware.painteddogs.app.PaintedDogsApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaintedDogsApp(
                isDarkTheme = isSystemInDarkTheme()
            )
        }
    }
}
