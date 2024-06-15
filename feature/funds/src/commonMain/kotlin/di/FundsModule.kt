package org.pointyware.painteddogs.feature.funds.di

import org.koin.dsl.module
import org.pointyware.painteddogs.core.data.di.dataQualifier
import org.pointyware.painteddogs.feature.funds.data.FundRepository
import org.pointyware.painteddogs.feature.funds.data.OfflineFirstCollectionRepository
import org.pointyware.painteddogs.feature.funds.interactors.CreateDonationUseCase
import org.pointyware.painteddogs.feature.funds.interactors.CreateDonationUseCaseImpl
import org.pointyware.painteddogs.feature.funds.interactors.SearchCollectionsUseCase
import org.pointyware.painteddogs.feature.funds.interactors.SearchCollectionsUseCaseImpl
import org.pointyware.painteddogs.feature.funds.local.CollectionCache
import org.pointyware.painteddogs.feature.funds.local.InMemoryCollectionCache
import org.pointyware.painteddogs.feature.funds.remote.CollectionApi
import org.pointyware.painteddogs.feature.funds.remote.TestCollectionApi
import org.pointyware.painteddogs.feature.funds.ui.FundDetailsUiStateMapper
import org.pointyware.painteddogs.feature.funds.ui.FundSearchUiStateMapper
import org.pointyware.painteddogs.feature.funds.viewmodels.FundDetailsViewModel
import org.pointyware.painteddogs.feature.funds.viewmodels.FundDetailsViewModelImpl
import org.pointyware.painteddogs.feature.funds.viewmodels.FundSearchViewModel
import org.pointyware.painteddogs.feature.funds.viewmodels.FundSearchViewModelImpl

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
    factory<FundSearchViewModel> { FundSearchViewModelImpl(get()) }
    factory<FundDetailsViewModel> { FundDetailsViewModelImpl() }
    factory<FundSearchViewModel> { FundSearchViewModelImpl(get()) }
}

fun featureFundsLocalModule() = module {
    single<CollectionCache> { InMemoryCollectionCache() }
}

fun featureFundsRemoteModule() = module {
    single<CollectionApi> { TestCollectionApi() }
}

fun featureFundsUiModule() = module {
    single<FundDetailsUiStateMapper> { FundDetailsUiStateMapper }
    single<FundSearchUiStateMapper> { FundSearchUiStateMapper }
}
