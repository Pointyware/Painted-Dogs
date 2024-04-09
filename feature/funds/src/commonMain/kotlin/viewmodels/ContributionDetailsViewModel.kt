package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * TODO: describe purpose/intent of ContributionDetailsViewModel
 */
interface ContributionDetailsViewModel {
    val state: StateFlow<ContributionDetailsUiState>

    fun onConfirm()
}

class ContributionDetailsViewModelImpl(): ContributionDetailsViewModel {
    private val mutableState = MutableStateFlow(ContributionDetailsUiState())
    override val state: StateFlow<ContributionDetailsUiState>
        get() = mutableState

    override fun onConfirm() {
        TODO("Not yet implemented")
    }
}
