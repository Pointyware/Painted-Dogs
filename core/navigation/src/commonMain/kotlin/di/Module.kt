package org.pointyware.painteddogs.core.navigation.di

/**
 * TODO: describe purpose/intent of Module
 */
fun coreNavigationModule() = org.koin.dsl.module {
    single { org.pointyware.painteddogs.core.navigation.NavigationInteractor(get()) }
    single { org.pointyware.painteddogs.core.navigation.NavigationViewModel(get()) }
}
