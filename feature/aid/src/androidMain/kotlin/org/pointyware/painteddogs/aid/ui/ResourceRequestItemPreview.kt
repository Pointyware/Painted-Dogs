package org.pointyware.painteddogs.aid.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import kotlin.time.Clock
import kotlin.time.Duration.Companion.hours
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Preview
@Composable
private fun ResourceRequestItemPreview(
    @PreviewParameter(ResourceRequestItemStateProvider::class)
    state: ResourceRequestItemState
) {
    PaintedDogsTheme {
        ResourceRequestItem(
            state = state,
            onProvideResource = { },
        )
    }
}


@OptIn(ExperimentalTime::class)
class ResourceRequestItemStateProvider: PreviewParameterProvider<ResourceRequestItemState> {
    override val values: Sequence<ResourceRequestItemState>
        get() = sequenceOf(
            ResourceRequestItemState(
                request = ResourceRequest(
                    category = Resource.Food,
                    description = "Some food item",
                    timePosted = Clock.System.now() - 1.hours
                )
            ),
            ResourceRequestItemState(
                request = ResourceRequest(
                    category = Resource.Housing,
                    description = "3 Beds",
                    timePosted = Clock.System.now() - 1.5.hours
                )
            ),
            ResourceRequestItemState(
                request = ResourceRequest(
                    category = Resource.Funds,
                    description = "50 for New Tire",
                    timePosted = Clock.System.now() - 5.7.hours
                )
            ),
            ResourceRequestItemState(
                request = ResourceRequest(
                    category = Resource.Skills,
                    description = "Budgeting",
                    timePosted = Clock.System.now() - 36.hours
                )
            ),
            ResourceRequestItemState(
                request = ResourceRequest(
                    category = Resource.Protection,
                    description = "Escort to School",
                    timePosted = Clock.System.now() - 300.3.hours
                )
            )
        )
}
