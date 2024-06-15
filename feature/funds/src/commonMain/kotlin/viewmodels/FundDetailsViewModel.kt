package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.navigation.Route
import org.pointyware.painteddogs.core.navigation.StackNavigationController
import org.pointyware.painteddogs.core.viewmodels.BaseViewModel
import org.pointyware.painteddogs.feature.funds.interactors.CreateFundUseCase

interface FundDetailsViewModel: BaseViewModel {
    fun onConfirm()

    val onBack: SharedFlow<Unit>
    val state: StateFlow<FundDetailsUiState>
    val loadingState: StateFlow<LoadingState>
}

class FundDetailsViewModelImpl(
    private val navController: StackNavigationController<Route<String>, Any>,
    private val createFundUseCase: CreateFundUseCase,
): FundDetailsViewModel {
    private val mutableLoadingState = MutableStateFlow<LoadingState>(LoadingState.Complete)
    override val loadingState: StateFlow<LoadingState>
        get() = mutableLoadingState

    private val mutableState = MutableStateFlow(FundDetailsUiState.Empty)
    override val state: StateFlow<FundDetailsUiState>
        get() = mutableState

    private val _onBack = MutableSharedFlow<Unit>()
    override val onBack: SharedFlow<Unit>
        get() = _onBack

    override fun onConfirm() {
        val currentState = mutableState.value
        val title = currentState.title
        val description = currentState.description
        val targetAmount = currentState.targetAmount ?: return
        viewModelScope.launch {
            mutableLoadingState.value = LoadingState.Loading
            createFundUseCase.invoke(
                title = title,
                description = description,
                targetAmount = targetAmount
            ).onSuccess {
                navController.goBack()
                mutableLoadingState.value = LoadingState.Complete
            }.onFailure {
                mutableLoadingState.value = LoadingState.LoadingError(it)
            }
        }
    }
}

data class FundDetailsUiState(
    val title: String,
    val description: String,
    val targetAmount: CurrencyAmount? = null
) {
    companion object {
        val Empty = FundDetailsUiState("", "", null)
    }
}

sealed class LoadingState(
    val isLoading: Boolean,
    val error: Throwable?
) {
    data object Loading: LoadingState(isLoading = true, error = null)
    data object Complete: LoadingState(isLoading = false, error = null)
    class LoadingError(error: Throwable): LoadingState(isLoading = false, error = error)
}
