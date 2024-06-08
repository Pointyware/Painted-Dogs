package org.pointyware.painteddogs.feature.collections.core.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.pointyware.painteddogs.core.navigation.di.NavigationDependencies

/**
 *
 */
interface FundDependencies {
    fun getNavigationDependencies(): NavigationDependencies
}

class KoinFundDependencies: FundDependencies, KoinComponent {
    override fun getNavigationDependencies(): NavigationDependencies = get()
}
