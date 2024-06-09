package org.pointyware.painteddogs.core.data.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.pointyware.painteddogs.core.data.FundsRepository
import org.pointyware.painteddogs.core.data.test.TestFundsRepository

val dataQualifier = named("data-scope")

/**
 * Collects all individual :core:data dependency bindings into a single module.
 */
fun dataModule() = module {
    single<DataDependencies> { KoinDataDependencies() }
    single<CoroutineScope>(qualifier = dataQualifier) { CoroutineScope(Dispatchers.IO + SupervisorJob()) }
    includes(
        repositoryModule()
    )
}

private fun repositoryModule() = module {
    single<FundsRepository> { TestFundsRepository() }
}
