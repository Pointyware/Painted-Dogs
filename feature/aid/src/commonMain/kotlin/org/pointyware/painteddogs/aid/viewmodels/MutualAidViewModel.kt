package org.pointyware.painteddogs.aid.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceExchange
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest

/**
 * Maintains the UI state for mutual aid views and responds to events to pass information
 * to the domain.
 */
class MutualAidViewModel(

): ViewModel() {


    private val _onClaimOffer = Channel<ResourceOffer>()
    val onClaimOffer: Flow<ResourceOffer> = _onClaimOffer.consumeAsFlow()
    private val _onProvideRequest = Channel<ResourceRequest>()
    val onProvideRequest: Flow<ResourceRequest> = _onProvideRequest.consumeAsFlow()
    private val _state = MutableStateFlow(MutualAidUiState.Default)
    val state: StateFlow<MutualAidUiState> = _state.asStateFlow()

    fun onSetResourceCategory(resource: Resource) {
        _state.update {
            it.copy(category = resource)
        }
    }

    fun onOfferClaim(resourceOffer: ResourceOffer) {

    }

    fun onRequestResponse(resourceRequest: ResourceRequest) {

    }

    fun onResourceFilterChanged(resources: Set<Resource>) {
        _state.update {
            it.copy(
                resourceFilter = resources
            )
        }
    }
}

data class MutualAidUiState(
    val category: Resource,
    val resourceFilter: Set<Resource>,
    val posts: List<ResourceExchange>
) {

    companion object {
        val Default = MutualAidUiState(
            category = Resource.Food,
            resourceFilter = Resource.entries.toSet(),
            posts = emptyList()
        )
    }
}
