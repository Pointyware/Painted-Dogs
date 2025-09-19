package org.pointyware.painteddogs.aid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
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

/**
 * A [org.pointyware.painteddogs.aid.viewmodels.RequestDraftViewModel] is a model of a view
 * for drafting a new request.
 */
@OptIn(ExperimentalUuidApi::class)
class RequestDraftViewModel(
    private val createRequestUseCase: CreateRequestUseCase
): ViewModel() {

    private val requestCreationChannel = Channel<Uuid>()

    /**
     * A flow that emits a new [Uuid] when a new request is created; does not re-emit
     * for new subscribers.
     */
    val requestCreationFlow: Flow<Uuid> = requestCreationChannel.consumeAsFlow()
    private val _state = MutableStateFlow(RequestDraftUiState.Default)

    /**
     *
     */
    val state: StateFlow<RequestDraftUiState> get() = _state
    private val _error = MutableStateFlow<Throwable?>(null)

    /**
     * [Throwable] error from some job or process that has run recently.
     */
    val error: StateFlow<Throwable?> get() = _error

    fun onTemporalScopeSelected(value: TemporalScope) {
        _state.update {
            it.copy(
                temporalScope = value
            )
        }
    }

    /**
     * Called when the request description is changed.
     */
    fun onDescriptionChanged(value: String) {
        _state.update {
            it.copy(
                description = value
            )
        }
    }

    /**
     * Called when the user is happy with their draft and wants to submit
     * their request to the network.
     */
    fun onSubmit() {
        requestCreationJob?.cancel()
        requestCreationJob = viewModelScope.launch {
            val state = state.value
            createRequestUseCase.invoke(
                description = state.description,
                category = state.category,
                scope = state.temporalScope
            )
                .onSuccess { resourceRequest ->
                    requestCreationChannel.send(resourceRequest.id)
                }
                .onFailure { throwable ->
                    _error.value = throwable
                }
        }
    }
    private var requestCreationJob: Job? = null

    fun onClearError() {
        _error.value = null
    }
}

/**
 *
 *
 * @param temporalScope
 * @param description
 * @param category
 */
data class RequestDraftUiState(
    val temporalScope: TemporalScope,
    val description: String,
    val category: Resource,
) {
    companion object Companion {
        val Default = RequestDraftUiState(
            temporalScope = TemporalScope.Indefinite,
            description = "",
            category = Resource.Food
        )
    }
}
