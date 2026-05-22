package org.pointyware.painteddogs.aid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.interactors.LoadResourceRequestUseCase
import org.pointyware.painteddogs.core.ui.org.pointyware.painteddogs.ui.LoadingUiState
import org.pointyware.painteddogs.entities.Quantity
import org.pointyware.painteddogs.entities.RealUnit
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Exposes a model to draft support details for a resource request.
 */
@OptIn(ExperimentalUuidApi::class)
class SupportRequestViewModel(
    private val loadRequestUseCase: LoadResourceRequestUseCase
): ViewModel() {

    private val mutableState = MutableStateFlow<LoadingUiState<SupportRequestUiState>>(LoadingUiState.Loading())
    val state: StateFlow<LoadingUiState<SupportRequestUiState>> get() = mutableState.asStateFlow()

    private val mutableError = MutableStateFlow<Throwable?>(null)
    val error: StateFlow<Throwable?> = mutableError.asStateFlow()

    fun loadRequest(requestId: String) {
        viewModelScope.launch {
            loadRequestUseCase.invoke(Uuid.parse(requestId))
                .onSuccess { request ->
                    mutableState.update {
                        LoadingUiState.Success(
                            SupportRequestUiState(
                                request = request,
                                quantity = request.quantity
                            )
                        )
                    }
                }
                .onFailure { throwable ->
                    mutableError.value = throwable
                }
        }
    }

}

data class SupportRequestUiState(
    val request: ResourceRequest,
    val quantity: Quantity<out RealUnit>,
)
