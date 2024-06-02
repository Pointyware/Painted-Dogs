package org.pointyware.painteddogs.core.viewmodels.di

/**
 * TODO: describe purpose/intent of Module
 */
fun coreViewModelsModule() = module {
    single { org.pointyware.painteddogs.core.viewmodels.DogDetailViewModel(get()) }
    single { org.pointyware.painteddogs.core.viewmodels.DogListViewModel(get()) }
}
