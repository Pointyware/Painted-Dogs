package org.pointyware.painteddogs.feature.collections.core.di

import org.koin.dsl.module

/**
 * Production funds feature module.
 */
fun featureFundsModule() = module {
    includes(
        featureInteractorsModule(),
        featureFundsDataModule(),
        featureLocalModule(),
        featureRemoteModule(),
        featureViewModelsModule(),
        featureUiModule(),
    )
}

fun featureFundsDataModule() = module {
    // single { CollectionRepositoryImpl(get(), get(), get(), get()) as CollectionRepository }
}

fun featureInteractorsModule() = module {
    // single { GetCollectionsUseCase(get()) }
    // single { GetCollectionUseCase(get()) }
    // single { CreateCollectionUseCase(get()) }
    // single { UpdateCollectionUseCase(get()) }
    // single { DeleteCollectionUseCase(get()) }
}

fun featureViewModelsModule() = module {
    // viewModel { CollectionsViewModel(get(), get(), get(), get(), get()) }
    // viewModel { CollectionViewModel(get(), get(), get(), get(), get()) }
}

fun featureLocalModule() = module {
    // single { LocalCollectionDataSource(get()) }
}

fun featureRemoteModule() = module {
    // single { RemoteCollectionDataSource(get()) }
}

fun featureUiModule() = module {
    // factory { CollectionAdapter() }
}
