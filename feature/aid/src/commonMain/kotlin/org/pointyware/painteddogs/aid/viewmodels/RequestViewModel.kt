package org.pointyware.painteddogs.aid.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.pointyware.painteddogs.aid.entities.TemporalScope

class RequestViewModel(

): ViewModel() {

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
