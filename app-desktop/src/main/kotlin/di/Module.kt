package org.pointyware.painteddogs.desktop.di

import org.koin.dsl.module
import org.pointyware.painteddogs.shared.entities.SharedFileResources
import org.pointyware.painteddogs.shared.entities.SharedStringResources
import org.pointyware.painteddogs.shared.ui.SharedDrawableResources
import org.pointyware.painteddogs.shared.ui.SharedFontResources

/**
 * Provides the desktop module
 */
fun desktopModule() = module {
    single<SharedStringResources> { DesktopStringResources() }
    single<SharedFontResources> { DesktopFontResources() }
    single<SharedDrawableResources> { DesktopDrawableResources() }
    single<SharedFileResources> { DesktopFileResources() }
}
