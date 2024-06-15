package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * TODO: describe purpose/intent of ContributionInfoViewModel
 */
interface ContributionInfoViewModel {
    val state: StateFlow<ContributionInfoUiState>
}

class ContributionInfoViewModelImpl: ContributionInfoViewModel {
    private val mutableState = MutableStateFlow(ContributionInfoUiState())
    override val state: StateFlow<ContributionInfoUiState>
        get() = mutableState
}
