package org.pointyware.painteddogs.shared.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.pointyware.painteddogs.shared.home.Home
import org.pointyware.painteddogs.shared.home.HomeUiStateMapper
import org.pointyware.painteddogs.shared.home.HomeViewModel
import org.pointyware.painteddogs.shared.home.HomeViewModelImpl
import kotlin.reflect.KClass

/**
 * Defines productions bindings to satisfy interface requests.
 */
fun homeModule() = module {
    single<HomeDependencies> { KoinHomeDependencies() }

    single<HomeUiStateMapper> { HomeUiStateMapper }
    single<HomeViewModel> { HomeViewModelImpl() }
    single<KClass<*>>(qualifier = named("Home")) { Home::class}
}
