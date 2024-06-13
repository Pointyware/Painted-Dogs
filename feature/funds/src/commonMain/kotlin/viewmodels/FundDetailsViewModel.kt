package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.pointyware.painteddogs.core.viewmodels.BaseViewModel

interface FundDetailsViewModel: BaseViewModel {

    abstract val state: StateFlow<FundDetailsUiState>
}

class FundDetailsViewModelImpl: FundDetailsViewModel {
    private val mutableState = MutableStateFlow(FundDetailsUiState.Empty)
    override val state: StateFlow<FundDetailsUiState>
        get() = mutableState
}

data class FundDetailsUiState(
    val title: String,
    val description: String,
) {
    companion object {
        val Empty = FundDetailsUiState("", "")
    }
}
