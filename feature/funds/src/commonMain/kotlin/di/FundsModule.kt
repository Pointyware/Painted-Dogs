package org.pointyware.painteddogs.feature.collections.core.di

import org.koin.dsl.module

/**
 * Production funds feature module.
 */
fun featureFundsModule() = module {
    includes(
        featureFundsInteractorsModule(),
        featureFundsDataModule(),
        featureFundsLocalModule(),
        featureFundsRemoteModule(),
        featureFundsViewModelsModule(),
        featureFundsUiModule(),
    )
}

fun featureFundsDataModule() = module {
    // single { CollectionRepositoryImpl(get(), get(), get(), get()) as CollectionRepository }
}

fun featureFundsInteractorsModule() = module {
    // single { GetCollectionsUseCase(get()) }
    // single { GetCollectionUseCase(get()) }
    // single { CreateCollectionUseCase(get()) }
    // single { UpdateCollectionUseCase(get()) }
    // single { DeleteCollectionUseCase(get()) }
}

fun featureFundsViewModelsModule() = module {
    // viewModel { CollectionsViewModel(get(), get(), get(), get(), get()) }
    // viewModel { CollectionViewModel(get(), get(), get(), get(), get()) }
}

fun featureFundsLocalModule() = module {
    // single { LocalCollectionDataSource(get()) }
}

fun featureFundsRemoteModule() = module {
    // single { RemoteCollectionDataSource(get()) }
}

fun featureFundsUiModule() = module {
    // factory { CollectionAdapter() }
}
