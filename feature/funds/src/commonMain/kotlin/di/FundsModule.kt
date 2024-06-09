package org.pointyware.painteddogs.feature.collections.core.di

import org.koin.dsl.module
import org.pointyware.painteddogs.core.data.di.dataQualifier
import org.pointyware.painteddogs.feature.collections.core.data.FundRepository
import org.pointyware.painteddogs.feature.collections.core.data.OfflineFirstCollectionRepository
import org.pointyware.painteddogs.feature.collections.core.interactors.CreateDonationUseCase
import org.pointyware.painteddogs.feature.collections.core.interactors.CreateDonationUseCaseImpl
import org.pointyware.painteddogs.feature.collections.core.interactors.SearchCollectionsUseCase
import org.pointyware.painteddogs.feature.collections.core.interactors.SearchCollectionsUseCaseImpl
import org.pointyware.painteddogs.feature.collections.core.local.CollectionCache
import org.pointyware.painteddogs.feature.collections.core.local.InMemoryCollectionCache
import org.pointyware.painteddogs.feature.collections.core.remote.CollectionApi
import org.pointyware.painteddogs.feature.collections.core.remote.TestCollectionApi
import org.pointyware.painteddogs.feature.collections.core.viewmodels.FundSearchViewModel
import org.pointyware.painteddogs.feature.collections.core.viewmodels.FundSearchViewModelImpl

/**
 * Production funds feature module.
 */
fun featureFundsModule() = module {
    single<FundDependencies> { KoinFundDependencies() }

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
    single<FundRepository> { OfflineFirstCollectionRepository(get(), get(), get(qualifier = dataQualifier)) }
}

fun featureFundsInteractorsModule() = module {
    factory<CreateDonationUseCase> { CreateDonationUseCaseImpl(get()) }
    factory<SearchCollectionsUseCase> { SearchCollectionsUseCaseImpl(get()) }
}

fun featureFundsViewModelsModule() = module {
    single<FundSearchViewModel> { FundSearchViewModelImpl(get()) }
}

fun featureFundsLocalModule() = module {
    single<CollectionCache> { InMemoryCollectionCache() }
}

fun featureFundsRemoteModule() = module {
    single<CollectionApi> { TestCollectionApi() }
}

fun featureFundsUiModule() = module {

}
