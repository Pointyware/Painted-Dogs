package org.pointyware.painteddogs.core.local.di

/**
 *
 */
val coreLocalModule = module {
    single { DogDao() }
    single { DogRepository(get()) }
    single { DogInteractor(get()) }
    single { DogListInteractor(get()) }
    single { DogDetailInteractor(get()) }
    single { DogDetailViewModel(get()) }
    single { DogListViewModel(get()) }
    single { DogDetailViewModel(get()) }
}
