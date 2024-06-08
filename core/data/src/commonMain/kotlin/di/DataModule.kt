package org.pointyware.painteddogs.core.data.di

import org.koin.dsl.module
import org.pointyware.painteddogs.core.data.FundsRepository
import org.pointyware.painteddogs.core.data.test.TestFundsRepository

/**
 * Collects all individual :core:data dependency bindings into a single module.
 */
fun dataModule() = module {
    single<DataDependencies> { KoinDataDependencies() }
    includes(
        repositoryModule()
    )
}

private fun repositoryModule() = module {
    single<FundsRepository> { TestFundsRepository() }
}
