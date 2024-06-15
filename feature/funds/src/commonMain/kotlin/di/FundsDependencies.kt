package org.pointyware.painteddogs.feature.funds.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.pointyware.painteddogs.core.navigation.di.NavigationDependencies
import org.pointyware.painteddogs.feature.funds.ui.ContributionDetailsUiStateMapper
import org.pointyware.painteddogs.feature.funds.ui.FundDetailsUiStateMapper
import org.pointyware.painteddogs.feature.funds.ui.FundInfoUiStateMapper
import org.pointyware.painteddogs.feature.funds.ui.FundSearchUiStateMapper
import org.pointyware.painteddogs.feature.funds.viewmodels.ContributionDetailsViewModel
import org.pointyware.painteddogs.feature.funds.viewmodels.FundDetailsViewModel
import org.pointyware.painteddogs.feature.funds.viewmodels.FundInfoViewModel
import org.pointyware.painteddogs.feature.funds.viewmodels.FundSearchViewModel

/**
 *
 */
interface FundDependencies {
    fun getNavigationDependencies(): NavigationDependencies
    fun getFundDetailsViewModel(): FundDetailsViewModel
    fun getFundDetailsUiStateMapper(): FundDetailsUiStateMapper
    fun getFundSearchViewModel(): FundSearchViewModel
    fun getFundSearchUiStateMapper(): FundSearchUiStateMapper
    fun getFundInfoViewModel(): FundInfoViewModel
    fun getFundInfoUiStateMapper(): FundInfoUiStateMapper
    fun getContributionDetailsViewModel(): ContributionDetailsViewModel
    fun getContributionDetailsUiStateMapper(): ContributionDetailsUiStateMapper
}

class KoinFundDependencies: FundDependencies, KoinComponent {
    override fun getNavigationDependencies(): NavigationDependencies = get()
    override fun getFundDetailsViewModel(): FundDetailsViewModel = get()
    override fun getFundDetailsUiStateMapper(): FundDetailsUiStateMapper = get()
    override fun getFundSearchViewModel(): FundSearchViewModel = get()
    override fun getFundSearchUiStateMapper(): FundSearchUiStateMapper = get()
    override fun getFundInfoViewModel(): FundInfoViewModel = get()
    override fun getFundInfoUiStateMapper(): FundInfoUiStateMapper = get()
    override fun getContributionDetailsViewModel(): ContributionDetailsViewModel = get()

    override fun getContributionDetailsUiStateMapper(): ContributionDetailsUiStateMapper = get()
}
