package org.pointyware.painteddogs.shared.di

import org.koin.dsl.module
import org.pointyware.painteddogs.shared.home.HomeUiStateMapper
import org.pointyware.painteddogs.shared.home.HomeViewModel
import org.pointyware.painteddogs.shared.home.HomeViewModelImpl

/**
 * Defines productions bindings to satisfy interface requests.
 */
fun homeModule() = module {
    single<HomeDependencies> { KoinHomeDependencies() }

    single<HomeUiStateMapper> { HomeUiStateMapper }
    single<HomeViewModel> { HomeViewModelImpl() }
}
