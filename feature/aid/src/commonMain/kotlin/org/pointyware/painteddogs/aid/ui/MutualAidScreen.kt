package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceExchange
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import kotlin.time.ExperimentalTime

data class ExchangeBoardScreenState(
    val posts: List<ResourceExchange>,
    val resources: Set<Resource>
)

@OptIn(ExperimentalTime::class)
@Composable
fun ExchangeBoardScreen(
    state: ExchangeBoardScreenState,
    onOfferClaim: (ResourceOffer)->Unit,
    onRequestResponse: (ResourceRequest)->Unit,
    onResourceFilterChanged: (Set<Resource>)->Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.posts) { post ->
                when (post) {
                    is ResourceRequest -> {
                        ResourceRequestItem(
                            ResourceRequestItemState(request = post),
                            onProvideResource = { onRequestResponse(post) },
                            modifier = Modifier,
                        )
                    }
                    is ResourceOffer -> {
                        ResourceOfferItem(
                            state = ResourceOfferItemState(offer = post),
                            onProvideResource = { onOfferClaim(post) },
                            modifier = Modifier
                        )
                    }
                }
            }
        }
        MultiResourceSelector(
            values = state.resources,
            onSelectionChanged = onResourceFilterChanged,
        )
    }
}
