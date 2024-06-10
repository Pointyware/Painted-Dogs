package org.pointyware.painteddogs.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.remember
import org.pointyware.painteddogs.shared.PaintedDogsApp
import org.pointyware.painteddogs.shared.di.getDependencies

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val appDependencies = remember { getDependencies() }
            PaintedDogsApp(
                dependencies = appDependencies,
                isDarkTheme = isSystemInDarkTheme()
            )
        }
    }
}
