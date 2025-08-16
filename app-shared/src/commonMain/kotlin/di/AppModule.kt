package org.pointyware.painteddogs.shared.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.pointyware.painteddogs.core.data.di.dataModule
import org.pointyware.painteddogs.core.entities.di.coreEntitiesModule
import org.pointyware.painteddogs.core.interactors.di.coreInteractorsModule
import org.pointyware.painteddogs.core.local.di.coreLocalModule
import org.pointyware.painteddogs.core.navigation.di.coreNavigationModule
import org.pointyware.painteddogs.core.remote.di.coreRemoteModule
import org.pointyware.painteddogs.core.ui.di.coreUiModule
import org.pointyware.painteddogs.core.viewmodels.di.coreViewModelsModule
import org.pointyware.painteddogs.feature.funds.di.featureFundsModule
import org.pointyware.painteddogs.feature.payments.di.featurePaymentsModule
import org.pointyware.painteddogs.feature.profiles.di.featureProfilesModule


fun appModule(): Module = module {
    includes(
        coreModule(),
        featureModule(),
        homeModule()
    )
}

fun coreModule() = module {
    includes(
        coreEntitiesModule(),
        coreInteractorsModule(),
        coreViewModelsModule(),
        dataModule(),
        coreLocalModule(),
        coreRemoteModule(),
        coreNavigationModule(),
        coreUiModule(),
    )
}

fun featureModule() = module {
    includes(
        featureFundsModule(),
        featureProfilesModule(),
        featurePaymentsModule(),
    )
}
