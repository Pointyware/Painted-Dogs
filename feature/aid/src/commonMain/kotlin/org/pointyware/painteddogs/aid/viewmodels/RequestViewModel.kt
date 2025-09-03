package org.pointyware.painteddogs.aid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.TemporalScope
import org.pointyware.painteddogs.aid.interactors.CreateRequestUseCase
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class RequestViewModel(
    private val createRequestUseCase: CreateRequestUseCase
): ViewModel() {

    private val _onRequestCreated = Channel<Uuid>()
    val onRequestCreated: Flow<Uuid> = _onRequestCreated.consumeAsFlow()
    private val _state = MutableStateFlow(RequestUiState.Default)
    val state: StateFlow<RequestUiState> get() = _state
    private val _error = MutableStateFlow<Throwable?>(null)
    val error: StateFlow<Throwable?> get() = _error

    fun onTemporalScopeSelected(value: TemporalScope) {
        _state.update {
            it.copy(
                temporalScope = value
            )
        }
    }

    fun onDescriptionChanged(value: String) {
        _state.update {
            it.copy(
                description = value
            )
        }
    }

    fun onSubmit() {
        val state = state.value
        viewModelScope.launch {
            createRequestUseCase.invoke(
                description = state.description,
                category = state.category,
                scope = state.temporalScope
            )
        }
    }

    fun onClearError() {
        _error.value = null
    }
}

data class RequestUiState(
    val temporalScope: TemporalScope,
    val description: String,
    val category: Resource,
) {
    companion object {
        val Default = RequestUiState(
            temporalScope = TemporalScope.Indefinite,
            description = "",
            category = Resource.Food
        )
    }
}
