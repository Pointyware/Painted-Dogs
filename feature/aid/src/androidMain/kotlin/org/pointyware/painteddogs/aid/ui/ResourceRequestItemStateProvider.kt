package org.pointyware.painteddogs.aid.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.entities.TemporalScope
import kotlin.time.Clock
import kotlin.time.Duration.Companion.hours
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
private val resourceRequestItemStateSequence = sequenceOf(
    ResourceRequestItemState(
        request = ResourceRequest(
            category = Resource.Food,
            scope = TemporalScope.Indefinite,
            description = "Some food item",
            timePosted = Clock.System.now() - 1.hours
        )
    ),
    ResourceRequestItemState(
        request = ResourceRequest(
            category = Resource.Housing,
            scope = TemporalScope.Indefinite,
            description = "3 Beds",
            timePosted = Clock.System.now() - 1.5.hours
        )
    ),
    ResourceRequestItemState(
        request = ResourceRequest(
            category = Resource.Funds,
            scope = TemporalScope.Indefinite,
            description = "50 for New Tire",
            timePosted = Clock.System.now() - 5.7.hours
        )
    ),
    ResourceRequestItemState(
        request = ResourceRequest(
            category = Resource.Skills,
            scope = TemporalScope.Indefinite,
            description = "Budgeting",
            timePosted = Clock.System.now() - 36.hours
        )
    ),
    ResourceRequestItemState(
        request = ResourceRequest(
            category = Resource.Protection,
            scope = TemporalScope.Indefinite,
            description = "Escort to School",
            timePosted = Clock.System.now() - 300.3.hours
        )
    )
)

@OptIn(ExperimentalTime::class)
class ResourceRequestItemStateProvider: PreviewParameterProvider<ResourceRequestItemState> {
    override val values: Sequence<ResourceRequestItemState>
        get() = resourceRequestItemStateSequence
}

private val temporalScopes = TemporalScope.entries.asSequence()
