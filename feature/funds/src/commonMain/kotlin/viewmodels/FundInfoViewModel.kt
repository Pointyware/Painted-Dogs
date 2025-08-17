package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * TODO: describe purpose/intent of FundInfoViewModel
 */
interface FundInfoViewModel {
    val state: StateFlow<FundInfoUiState>
}

@OptIn(ExperimentalUuidApi::class)
class FundInfoViewModelImpl : FundInfoViewModel {
    private val mutableState = MutableStateFlow(FundInfoUiState(Uuid.random(), ""))
    override val state: StateFlow<FundInfoUiState>
        get() = mutableState
}
