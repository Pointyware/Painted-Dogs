package org.pointyware.painteddogs.core.data.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.pointyware.painteddogs.core.data.FundsRepository
import org.pointyware.painteddogs.core.data.test.TestFundsRepository
import kotlin.coroutines.CoroutineContext

val dataQualifier = named("data-scope")

/**
 * Collects all individual :core:data dependency bindings into a single module.
 */
fun dataModule() = module {
    single<CoroutineContext>(qualifier = dataQualifier) { Dispatchers.IO + SupervisorJob() }
    single<CoroutineScope>(qualifier = dataQualifier) { CoroutineScope(get<CoroutineContext>(qualifier = dataQualifier)) }
    includes(
        repositoryModule()
    )
}

private fun repositoryModule() = module {
    single<FundsRepository> { TestFundsRepository() }
}
