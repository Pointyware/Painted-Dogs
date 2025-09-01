package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.Res
import org.pointyware.painteddogs.aid.acc_dropdown
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceExchange
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.LocalGeometry
import kotlin.time.ExperimentalTime

data class ExchangeBoardScreenState(
    val category: Resource,
    val posts: List<ResourceExchange>,
    val resources: Set<Resource>
)

@Composable
fun Modifier.cardStyle(): Modifier {
    return this
        .padding(LocalGeometry.current.paddingSmall)
        .shadow(
            elevation = 8.dp,
            shape = MaterialTheme.shapes.large
        )
        .fillMaxWidth()
}

@OptIn(ExperimentalTime::class)
@Composable
fun ExchangeBoardScreen(
    state: ExchangeBoardScreenState,
    onOfferClaim: (ResourceOffer)->Unit,
    onRequestResponse: (ResourceRequest)->Unit,
    onResourceFilterChanged: (Set<Resource>)->Unit,
    onResourceCategoryChanged: (Resource)->Unit,
    onCreateOffer: (Resource)->Unit
) {
    val layoutDirection = LocalLayoutDirection.current
    val padding = LocalGeometry.current.paddingMedium
    Column (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(
                top = padding.calculateTopPadding(),
                start = padding.calculateStartPadding(layoutDirection),
                end = padding.calculateEndPadding(layoutDirection),
                bottom = 0.dp
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(GeometryTokens.dpMedium)
    ) {
        MultiResourceSelector(
            values = state.resources,
            onSelectionChanged = onResourceFilterChanged,
        )
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(state.posts) { post ->
                when (post) {
                    is ResourceRequest -> {
                        ResourceRequestItem(
                            ResourceRequestItemState(request = post),
                            onProvideResource = { onRequestResponse(post) },
                            modifier = Modifier
                                .cardStyle()
                                .animateItem()
                        )
                    }
                    is ResourceOffer -> {
                        ResourceOfferItem(
                            state = ResourceOfferItemState(offer = post),
                            onClaimOffer = { onOfferClaim(post) },
                            modifier = Modifier
                                .cardStyle()
                                .animateItem()
                        )
                    }
                }
            }
        }
        Box {
            var menuOpen by remember { mutableStateOf(false) }
            Row {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onCreateOffer(state.category)
                    }
                ) {
                    Text(
                        style = MaterialTheme.typography.labelMedium,
                        text = stringForResource(state.category)
                    )
                }
                IconButton(
                    onClick = {
                        menuOpen = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = stringResource(Res.string.acc_dropdown)
                    )
                }
            }
            DropdownMenu(
                expanded = menuOpen,
                onDismissRequest = { menuOpen = false },
                modifier = Modifier.clickable {
                    menuOpen = true
                }
            ) {
                Resource.entries.forEach { resource ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                style = MaterialTheme.typography.labelMedium,
                                text = stringForResource(resource)
                            )
                        },
                        onClick = {
                            onResourceCategoryChanged(resource)
                            menuOpen = false
                        },
                    )
                }
            }
        }
    }
}
