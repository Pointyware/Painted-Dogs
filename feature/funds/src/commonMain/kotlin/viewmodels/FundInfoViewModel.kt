package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * TODO: describe purpose/intent of FundInfoViewModel
 */
interface FundInfoViewModel {
    val state: StateFlow<FundInfoUiState>
}

class FundInfoViewModelImpl : FundInfoViewModel {
    private val mutableState = MutableStateFlow(FundInfoUiState(""))
    override val state: StateFlow<FundInfoUiState>
        get() = mutableState
}
