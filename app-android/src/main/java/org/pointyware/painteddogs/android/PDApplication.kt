package org.pointyware.painteddogs.android

import android.app.Application
import org.koin.core.context.startKoin
import org.pointyware.painteddogs.android.di.androidModule
import org.pointyware.painteddogs.shared.di.appModule

/**
 * This is the production Painted Dogs application; it performs production environment setup.
 */
class PDApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                androidModule(),
                appModule()
            )
        }
    }
}
