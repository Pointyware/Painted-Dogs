package org.pointyware.painteddogs.feature.profiles.di

import org.koin.dsl.module
import org.pointyware.painteddogs.feature.collections.core.viewmodels.ContributionHistoryViewModel
import org.pointyware.painteddogs.feature.collections.core.viewmodels.ContributionHistoryViewModelImpl
import org.pointyware.painteddogs.feature.profiles.viewmodels.ProfileViewModel
import org.pointyware.painteddogs.feature.profiles.viewmodels.ProfileViewModelImpl

/**
 */
fun featureProfilesModule() = module {
    single<ProfileDependencies> { KoinProfileDependencies() }

    factory<ProfileViewModel> { ProfileViewModelImpl() }
    factory<ContributionHistoryViewModel> { ContributionHistoryViewModelImpl() }
}
