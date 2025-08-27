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
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.TemporalScope
import org.pointyware.painteddogs.aid.interactors.CreateOfferUseCase
import org.pointyware.painteddogs.core.navigation.Destination
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class OfferViewModel(
    private val createOfferUseCase: CreateOfferUseCase
): ViewModel() {

    private val _navEvent = Channel<Destination>()
    val navEvent: Flow<Destination> = _navEvent.consumeAsFlow()
    private val _state = MutableStateFlow(OfferUiState.Default)
    val state: StateFlow<OfferUiState> = _state.asStateFlow()

    fun onSelectTemporalScope(value: TemporalScope) {
        _state.update {
            it.copy(scope = value)
        }
    }

    fun onChangeTitle(title: String) {
        _state.update {
            it.copy(title = title)
        }
    }

    fun onSubmit() {
        viewModelScope.launch {
            val state = state.value
            createOfferUseCase.invoke(
                title = state.title,
                description = "",
                offer = ResourceOffer(
                    TODO(),
                    TODO(),
                    TODO()
                ),
            )
        }
    }
}

sealed interface DetailUiState {

    val description: String

    data class FoodDetailUiState(
        override val description: String
    ): DetailUiState

    data class HousingDetailUiState(
        override val description: String

    ): DetailUiState

    data class FundsDetailUiState(
        override val description: String
    ): DetailUiState

    data class SkillsDetailUiState(
        override val description: String
    ): DetailUiState

    data class ProtectionDetailUiState(
        override val description: String
    ): DetailUiState
}
data class OfferUiState(
    val title: String,
    val scope: TemporalScope,
    val detailUiState: DetailUiState
) {
    companion object {
        val Default = OfferUiState(
            title = "",
            scope = TemporalScope.Indefinite,
            detailUiState = DetailUiState.FoodDetailUiState(
                ""
            )
        )
    }
}
