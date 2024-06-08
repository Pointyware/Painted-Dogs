package org.pointyware.painteddogs.core.navigation.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.pointyware.painteddogs.core.navigation.StackNavigationController

/**
 *
 */
interface NavigationDependencies {
    fun getNavController(): StackNavigationController<String?, Any>
}

class KoinNavigationDependencies: NavigationDependencies, KoinComponent {
    override fun getNavController(): StackNavigationController<String?, Any> = get(parameters = { parametersOf(null)})
}
