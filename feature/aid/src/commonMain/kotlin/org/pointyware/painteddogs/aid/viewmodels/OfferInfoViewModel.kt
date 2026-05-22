package org.pointyware.painteddogs.aid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.pointyware.painteddogs.aid.data.AidRepository
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class OfferInfoViewModel(
    private val repository: AidRepository,
): ViewModel() {

    private val _state = MutableStateFlow<OfferInfoUiState?>(null)
    val state: StateFlow<OfferInfoUiState?> = _state.asStateFlow()
    private val _error = MutableStateFlow<Throwable?>(null)
    val error: StateFlow<Throwable?> = _error.asStateFlow()

    fun onInit(id: Uuid) {
        viewModelScope.launch {
            repository.getOffer(id)
                .onSuccess { offer ->
                    _state.value = OfferInfoUiState(offer)
                }
                .onFailure { throwable ->
                    _error.value = throwable
                }
        }
    }

    fun onClearError() {
        _error.value = null
    }
}

data class OfferInfoUiState(
    val offer: ResourceOffer
) {

}
