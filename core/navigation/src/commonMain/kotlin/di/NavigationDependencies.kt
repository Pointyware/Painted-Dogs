package org.pointyware.painteddogs.core.navigation.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.pointyware.painteddogs.core.navigation.Route
import org.pointyware.painteddogs.core.navigation.StackNavigationController

/**
 *
 */
interface NavigationDependencies {
    fun getNavController(): StackNavigationController<Route<String>, Any>
}

class KoinNavigationDependencies: NavigationDependencies, KoinComponent {
    override fun getNavController(): StackNavigationController<Route<String>, Any> = get()
}
