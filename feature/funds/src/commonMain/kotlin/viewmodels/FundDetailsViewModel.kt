package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.pointyware.painteddogs.core.navigation.Route
import org.pointyware.painteddogs.core.navigation.StackNavigationController
import org.pointyware.painteddogs.core.navigation.route
import org.pointyware.painteddogs.core.viewmodels.BaseViewModel
import org.pointyware.painteddogs.feature.funds.data.FundRepository

interface FundDetailsViewModel: BaseViewModel {
    fun onConfirm()

    val onBack: SharedFlow<Unit>
    val state: StateFlow<FundDetailsUiState>
}

class FundDetailsViewModelImpl(
    private val navController: StackNavigationController<Route<String>, Any>,
    private val fundRepository: FundRepository,
): FundDetailsViewModel {
    private val mutableState = MutableStateFlow(FundDetailsUiState.Empty)
    override val state: StateFlow<FundDetailsUiState>
        get() = mutableState

    private val _onBack = MutableSharedFlow<Unit>()
    override val onBack: SharedFlow<Unit>
        get() = _onBack

    override fun onConfirm() {
        TODO("Run transaction; Submit new contribution to the fund")
        navController.navigateTo(route())
    }
}

data class FundDetailsUiState(
    val title: String,
    val description: String,
) {
    companion object {
        val Empty = FundDetailsUiState("", "")
    }
}
