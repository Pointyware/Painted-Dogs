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
    includes(

    )
}

private fun coreModule(): Module = module {
    includes(
        entitiesModule()
    )
}

private fun entitiesModule(): Module = module {

}

private fun interactorsModule(): Module = module {

}

private fun uiModule(): Module = module {

}
