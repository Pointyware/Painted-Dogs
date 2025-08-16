package org.pointyware.painteddogs.android.di

import org.koin.dsl.module
import org.pointyware.painteddogs.shared.entities.SharedStringResources

/**
 *
 */
fun androidModule() = module {
    single<SharedStringResources> { AndroidStringResources() }
}
