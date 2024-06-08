package org.pointyware.painteddogs.core.navigation.di

import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.pointyware.painteddogs.core.navigation.StackNavigationController
import org.pointyware.painteddogs.core.navigation.StackNavigationControllerImpl

/**
 *
 */
fun coreNavigationModule() = module {
    single<NavigationDependencies> { KoinNavigationDependencies() }

    single<StackNavigationController<String?, Any>> {
        StackNavigationControllerImpl(it.get())
    }
}
