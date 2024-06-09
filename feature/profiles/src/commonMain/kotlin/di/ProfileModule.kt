package org.pointyware.painteddogs.feature.profiles.di

import org.koin.dsl.module

/**
 */
fun featureProfilesModule() = module {
    single<ProfileDependencies> { KoinProfileDependencies() }

}
