package org.pointyware.painteddogs.core.navigation.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.pointyware.painteddogs.core.navigation.StackNavigationController
import org.pointyware.painteddogs.core.navigation.StackNavigationControllerImpl

/**
 *
 */
fun coreNavigationModule() = module {
    single<NavigationDependencies> { KoinNavigationDependencies() }

    single<StackNavigationController> {
        StackNavigationControllerImpl(get(qualifier = named("Home")))
    }
}
