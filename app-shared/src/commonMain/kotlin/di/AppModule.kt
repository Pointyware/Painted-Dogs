package org.pointyware.painteddogs.app.di

import di.coreInteractorsModule
import org.koin.core.module.Module
import org.koin.dsl.module
import org.pointyware.painteddogs.core.data.di.coreDataModule
import org.pointyware.painteddogs.core.entities.di.coreEntitiesModule
import org.pointyware.painteddogs.core.local.di.coreLocalModule
import org.pointyware.painteddogs.core.remote.di.coreRemoteModule
import org.pointyware.painteddogs.core.viewmodels.di.coreViewModelsModule
import org.pointyware.painteddogs.feature.collections.core.di.featureFundsModule


interface Dependencies {

}

fun MockDependencies(): Dependencies = object : Dependencies {

}

/**
 * setup koin app-level module
 */
suspend fun getDependencies(): Dependencies = PlatformDependencies()


expect class PlatformDependencies(): Dependencies {

}

fun appModule(): Module = module {
    includes(
        coreEntitiesModule(),
        coreInteractorsModule(),
        coreViewModelsModule(),
        coreDataModule(),
        coreLocalModule(),
        coreRemoteModule(),
        featureFundsModule(),
    )
}
