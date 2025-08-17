package org.pointyware.painteddogs.android.di

import org.koin.dsl.module
import org.pointyware.painteddogs.shared.data.SharedFileResources
import org.pointyware.painteddogs.shared.entities.SharedStringResources
import org.pointyware.painteddogs.shared.ui.SharedDrawableResources
import org.pointyware.painteddogs.shared.ui.SharedFontResources

/**
 *
 */
fun androidModule() = module {
    single<SharedStringResources> { AndroidStringResources() }
    single<SharedDrawableResources> { AndroidDrawableResources() }
    single<SharedFileResources> { AndroidFileResources() }
    single<SharedFontResources> { AndroidFontResources() }
}
