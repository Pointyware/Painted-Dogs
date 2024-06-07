package org.pointyware.painteddogs.core.data.di

import org.koin.dsl.module

/**
 * Collects all individual :core:data dependency bindings into a single module.
 */
fun coreDataModule() = module {
    includes(
        repositoryModule()
    )
}

private fun repositoryModule() = module {
    single {  }
}
