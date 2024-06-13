package org.pointyware.painteddogs.feature.funds.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.pointyware.painteddogs.core.navigation.di.NavigationDependencies
import org.pointyware.painteddogs.feature.funds.ui.FundDetailsUiStateMapper
import org.pointyware.painteddogs.feature.funds.viewmodels.FundDetailsViewModel

/**
 *
 */
interface FundDependencies {
    fun getNavigationDependencies(): NavigationDependencies
    fun getFundDetailsViewModel(): FundDetailsViewModel
    fun getFundDetailsUiStateMapper(): FundDetailsUiStateMapper
}

class KoinFundDependencies: FundDependencies, KoinComponent {
    override fun getNavigationDependencies(): NavigationDependencies = get()
    override fun getFundDetailsViewModel(): FundDetailsViewModel = get()
    override fun getFundDetailsUiStateMapper(): FundDetailsUiStateMapper {
        return FundDetailsUiStateMapper
    }
}
