package org.pointyware.painteddogs.aid.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.core.navigation.Destination

/**
 * Maintains the UI state for mutual aid views and responds to events to pass information
 * to the domain.
 */
class MutualAidViewModel(

): ViewModel() {


    private val _navEvent = Channel<Destination>()
    val navEvent: Flow<Destination> = _navEvent.consumeAsFlow()
    private val _state = MutableStateFlow(MutualAidUiState.Default)
    val state: StateFlow<MutualAidUiState> = _state.asStateFlow()


    fun onSetFilter(filter: Set<Resource>) {

    }

    fun onOfferClaim(resourceOffer: ResourceOffer) {

    }

    fun onRequestResponse(resourceRequest: ResourceRequest) {

    }
}

data class MutualAidUiState(
    val resourceFilter: Set<Resource>,
    val posts: List<Any>
) {

    companion object {
        val Default = MutualAidUiState(
            resourceFilter = Resource.entries.toSet(),
            posts = emptyList()
        )
    }
}
