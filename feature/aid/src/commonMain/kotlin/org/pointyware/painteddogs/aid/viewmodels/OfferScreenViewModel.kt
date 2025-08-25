package org.pointyware.painteddogs.aid.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import org.pointyware.painteddogs.core.navigation.Destination

class OfferScreenViewModel: ViewModel() {

    private val _navEvent = Channel<Destination>()
    val navEvent: Flow<Destination> = _navEvent.consumeAsFlow()
    private val _state = MutableStateFlow(OfferUiState.Default)
    val state: StateFlow<OfferUiState> = _state.asStateFlow()

    fun onSubmit() {

    }

}

data class OfferUiState(
    val title: String,

) {
    companion object {
        val Default = OfferUiState(
            title = "",

        )
    }
}
