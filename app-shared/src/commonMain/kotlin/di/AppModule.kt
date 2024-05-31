package org.pointyware.painteddogs.app.di

import org.koin.core.module.Module
import org.koin.dsl.module


interface Dependencies {

}

fun MockDependencies(): Dependencies = object : Dependencies {

}

/**
 * setup koin app-level module
 */
suspend fun getDependencies(): Dependencies = PlatformDependencies()


expect class PlatformDependencies(): Dependencies {

}

fun appModule(): Module = module {
    TODO("not implemented")
}
