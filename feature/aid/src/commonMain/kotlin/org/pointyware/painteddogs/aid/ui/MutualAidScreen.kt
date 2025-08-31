package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceExchange
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.LocalGeometry
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
    val layoutDirection = LocalLayoutDirection.current
    val padding = LocalGeometry.current.paddingMedium
    Column (
        modifier = Modifier
            .padding(
                top = padding.calculateTopPadding(),
                start = padding.calculateStartPadding(layoutDirection),
                end = padding.calculateEndPadding(layoutDirection),
                bottom = 0.dp
            )
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(GeometryTokens.dpMedium)
    ) {
        MultiResourceSelector(
            values = state.resources,
            onSelectionChanged = onResourceFilterChanged,
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.posts) { post ->
                when (post) {
                    is ResourceRequest -> {
                        ResourceRequestItem(
                            ResourceRequestItemState(request = post),
                            onProvideResource = { onRequestResponse(post) },
                            modifier = Modifier
                                .shadow(
                                    elevation = 8.dp,
                                )
                                .padding(GeometryTokens.dpLarge)
                                .clip(shape = MaterialTheme.shapes.medium)
                                .fillMaxWidth()
                                .animateItem()
                        )
                    }
                    is ResourceOffer -> {
                        ResourceOfferItem(
                            state = ResourceOfferItemState(offer = post),
                            onClaimOffer = { onOfferClaim(post) },
                            modifier = Modifier
                                .shadow(
                                    elevation = 8.dp,
                                )
                                .padding(GeometryTokens.dpLarge)
                                .clip(shape = MaterialTheme.shapes.medium)
                                .fillMaxWidth()
                                .animateItem()
                        )
                    }
                }
            }
        }
    }
}
