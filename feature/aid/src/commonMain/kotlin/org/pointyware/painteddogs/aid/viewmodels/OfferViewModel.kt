package org.pointyware.painteddogs.aid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.TemporalScope
import org.pointyware.painteddogs.aid.interactors.CreateOfferUseCase
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class OfferViewModel(
    private val createOfferUseCase: CreateOfferUseCase
): ViewModel() {

    private val _onOfferCreated = Channel<ResourceOffer>()
    val onOfferCreated: Flow<ResourceOffer> = _onOfferCreated.consumeAsFlow()
    private val _state = MutableStateFlow(OfferUiState.Default)
    val state: StateFlow<OfferUiState> = _state.asStateFlow()
    private val _error = MutableStateFlow<Throwable?>(null)
    val error: StateFlow<Throwable?> = _error.asStateFlow()

    fun onSelectTemporalScope(value: TemporalScope) {
        _state.update {
            it.copy(scope = value)
        }
    }

    fun onChangeDescription(description: String) {
        _state.update {
            it.copy(description = description)
        }
    }

    fun onSetResourceCategory(resource: Resource) {
        _state.update {
            it.copy(category = resource)
        }
    }

    fun onClearError() {
        _error.value = null
    }

    fun onSubmit() {
        viewModelScope.launch {
            val state = state.value
            createOfferUseCase.invoke(
                description = state.description,
                scope = state.scope,
                category = state.category)
                .onSuccess { offer ->
                    _onOfferCreated.send(offer)
                }
                .onFailure { throwable ->
                    _error.value = throwable
                }
        }
    }
}

sealed interface DetailUiState {

    val description: String
    val category: Resource

    data class FoodDetailUiState(
        override val description: String
    ): DetailUiState {
        override val category: Resource
            get() = Resource.Food
    }

    data class HousingDetailUiState(
        override val description: String

    ): DetailUiState {
        override val category: Resource
            get() = Resource.Housing
    }

    data class FundsDetailUiState(
        override val description: String
    ): DetailUiState {
        override val category: Resource
            get() = Resource.Funds
    }

    data class SkillsDetailUiState(
        override val description: String
    ): DetailUiState {
        override val category: Resource
            get() = Resource.Skills
    }

    data class ProtectionDetailUiState(
        override val description: String
    ): DetailUiState {
        override val category: Resource
            get() = Resource.Protection
    }
}
data class OfferUiState(
    val description: String,
    val category: Resource,
    val scope: TemporalScope,
) {
    companion object {
        val Default = OfferUiState(
            description = "",
            category = Resource.Food,
            scope = TemporalScope.Indefinite
        )
    }
}
