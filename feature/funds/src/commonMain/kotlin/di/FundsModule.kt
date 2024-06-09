package org.pointyware.painteddogs.feature.collections.core.di

import org.koin.dsl.module
import org.pointyware.painteddogs.core.navigation.di.coreNavigationModule
import org.pointyware.painteddogs.feature.collections.core.interactors.CreateDonationUseCase
import org.pointyware.painteddogs.feature.collections.core.interactors.CreateDonationUseCaseImpl
import org.pointyware.painteddogs.feature.collections.core.interactors.SearchCollectionsUseCase
import org.pointyware.painteddogs.feature.collections.core.interactors.SearchCollectionsUseCaseImpl

/**
 * Production funds feature module.
 */
fun featureFundsModule() = module {
    single<FundDependencies> { KoinFundDependencies() }

    includes(
        coreNavigationModule(),

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
    factory<CreateDonationUseCase> { CreateDonationUseCaseImpl(get()) }
    factory<SearchCollectionsUseCase> { SearchCollectionsUseCaseImpl(get()) }
}

fun featureFundsViewModelsModule() = module {

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
