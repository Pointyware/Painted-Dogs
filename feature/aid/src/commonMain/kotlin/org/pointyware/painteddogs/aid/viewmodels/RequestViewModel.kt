package org.pointyware.painteddogs.aid.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.entities.TemporalScope
import org.pointyware.painteddogs.aid.interactors.CreateRequestUseCase

class RequestViewModel(
    private val createRequestUseCase: CreateRequestUseCase
): ViewModel() {

    private val _onRequestCreated = Channel<ResourceRequest>()
    val onRequestCreated: Flow<ResourceRequest> = _onRequestCreated.consumeAsFlow()
    private val _state = MutableStateFlow(RequestUiState.Default)
    val state: StateFlow<RequestUiState> get() = _state

    fun onTemporalScopeSelected(value: TemporalScope) {
        _state.update {
            it.copy(
                temporalScope = value
            )
        }
    }

    fun onSubmit() {

    }
}

data class RequestUiState(
    val temporalScope: TemporalScope,
    val description: String,
) {
    companion object {
        val Default = RequestUiState(
            temporalScope = TemporalScope.Indefinite,
            description = ""
        )
    }
}
