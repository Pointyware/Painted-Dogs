package org.pointyware.painteddogs.core.navigation.di

import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.pointyware.painteddogs.core.navigation.Route
import org.pointyware.painteddogs.core.navigation.StackNavigationController
import org.pointyware.painteddogs.core.navigation.StackNavigationControllerImpl
import org.pointyware.painteddogs.core.navigation.route

/**
 *
 */
fun coreNavigationModule() = module {
    single<NavigationDependencies> { KoinNavigationDependencies() }

    single<StackNavigationController<Route<String>, Any>> {
        StackNavigationControllerImpl(route())
    }
}
