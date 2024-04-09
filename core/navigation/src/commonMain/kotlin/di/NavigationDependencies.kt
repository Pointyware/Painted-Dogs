package org.pointyware.painteddogs.core.navigation.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.pointyware.painteddogs.core.navigation.StackNavigationController

/**
 *
 */
interface NavigationDependencies {
    fun getNavController(): StackNavigationController
}

class KoinNavigationDependencies: NavigationDependencies, KoinComponent {
    override fun getNavController(): StackNavigationController = get()
}
