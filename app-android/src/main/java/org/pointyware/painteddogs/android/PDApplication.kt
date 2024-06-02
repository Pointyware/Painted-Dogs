package org.pointyware.painteddogs.android

import android.app.Application
import org.koin.core.context.startKoin
import org.pointyware.painteddogs.app.di.appModule

/**
 * This is the production Painted Dogs application; it performs production environment setup.
 */
class PDApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                appModule()
            )
        }
    }
}
