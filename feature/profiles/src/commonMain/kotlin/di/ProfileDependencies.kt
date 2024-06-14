package org.pointyware.painteddogs.feature.profiles.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.pointyware.painteddogs.feature.funds.viewmodels.ContributionHistoryViewModel
import org.pointyware.painteddogs.feature.profiles.ui.ProfileUiStateMapper
import org.pointyware.painteddogs.feature.profiles.viewmodels.ProfileViewModel

/**
 */
interface ProfileDependencies {
    fun getProfileViewModel(): ProfileViewModel
    fun getContributionHistoryViewModel(): ContributionHistoryViewModel
    fun getProfileUiStateMapper(): ProfileUiStateMapper
}

class KoinProfileDependencies: ProfileDependencies, KoinComponent {
    override fun getProfileViewModel(): ProfileViewModel = get()

    override fun getContributionHistoryViewModel(): ContributionHistoryViewModel = get()
    override fun getProfileUiStateMapper(): ProfileUiStateMapper = get()
}
