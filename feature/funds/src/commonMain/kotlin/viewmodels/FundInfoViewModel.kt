package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.pointyware.painteddogs.core.entities.Uuid

/**
 * TODO: describe purpose/intent of FundInfoViewModel
 */
interface FundInfoViewModel {
    val state: StateFlow<FundInfoUiState>
}

class FundInfoViewModelImpl : FundInfoViewModel {
    private val mutableState = MutableStateFlow(FundInfoUiState(Uuid.v4(), ""))
    override val state: StateFlow<FundInfoUiState>
        get() = mutableState
}
