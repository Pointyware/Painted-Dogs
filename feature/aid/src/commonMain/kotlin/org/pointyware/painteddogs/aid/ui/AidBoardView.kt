package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceExchange
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.viewmodels.MutualAidUiState
import org.pointyware.painteddogs.core.ui.design.LocalDateFormat
import kotlin.time.ExperimentalTime

interface AidBoardViewState {
    val posts: List<ResourceExchange>
    val resources: Set<Resource>
}

data class AidBoardViewModel(
    private val mutualAidUiState: MutualAidUiState
): AidBoardViewState {
    override val posts: List<ResourceExchange> get() = mutualAidUiState.posts
    override val resources: Set<Resource> get() = mutualAidUiState.resourceFilter
}

@OptIn(ExperimentalTime::class)
@Composable
fun AidBoardView(
    state: AidBoardViewState,
    onOfferClaim: (ResourceOffer)->Unit,
    onRequestResponse: (ResourceRequest)->Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.posts) { post ->
                when (post) {
                    is ResourceRequest -> {
                        ResourceRequestItem(
                            ResourceRequestItemState(
                                request = post,
                                timePosted = LocalDateFormat.current.format(post.timePosted),
                            ),
                            onProvideResource = { onRequestResponse(post) },
                            modifier = Modifier,
                        )
                    }
                    is ResourceOffer -> {
                        ResourceOfferItem(
                            state = ResourceOfferItemState(
                                offer = post,
                                timePosted = LocalDateFormat.current.format(post.timePosted),
                            ),
                            onProvideResource = { onOfferClaim(post) },
                            modifier = Modifier
                        )
                    }
                }
            }
        }
        var resource by remember { mutableStateOf(Resource.Food) }
        SingleResourceSelector(
            value = resource,
            onResourceSelected = { resource = it },
            modifier = Modifier
        )
    }
}
