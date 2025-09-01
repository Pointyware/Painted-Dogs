package org.pointyware.painteddogs.aid.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.entities.TemporalScope
import org.pointyware.painteddogs.common.later
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
@PreviewLightDark
@PreviewDynamicColors
@Composable
private fun AidBoardViewPreview() {
    val timeZone = TimeZone.currentSystemDefault()
    val referenceDateTime = LocalDateTime(
        year = 2025,
        month = 8,
        day = 1,
        hour = 20,
        minute = 40,
        second = 2
    )
    val nextDateTime = referenceDateTime.later(hour = 1, minute = 5)

    PaintedDogsTheme {
        ExchangeBoardScreen(
            state = ExchangeBoardScreenState(
                posts = listOf(
                    ResourceOffer(
                        id = Uuid.random(),
                        category = Resource.Food,
                        scope = TemporalScope.Indefinite,
                        description = "Lots of rice",
                        timePosted = referenceDateTime.toInstant(timeZone)
                    ),
                    ResourceRequest(
                        id = Uuid.random(),
                        category = Resource.Protection,
                        scope = TemporalScope.Immediate,
                        description = "Escort to School",
                        timePosted = nextDateTime.toInstant(timeZone)
                    ),
                    ResourceRequest(
                        id = Uuid.random(),
                        category = Resource.Protection,
                        scope = TemporalScope.Schedule,
                        description = "Escort to School",
                        timePosted = nextDateTime.toInstant(timeZone)
                    ),
                    ResourceOffer(
                        id = Uuid.random(),
                        category = Resource.Food,
                        scope = TemporalScope.Limited,
                        description = "Lots of rice",
                        timePosted = referenceDateTime.toInstant(timeZone)
                    ),
                    ResourceRequest(
                        id = Uuid.random(),
                        category = Resource.Protection,
                        scope = TemporalScope.Limited,
                        description = "Escort to School",
                        timePosted = nextDateTime.toInstant(timeZone)
                    ),
                    ResourceOffer(
                        id = Uuid.random(),
                        category = Resource.Food,
                        scope = TemporalScope.Indefinite,
                        description = "Lots of rice",
                        timePosted = referenceDateTime.toInstant(timeZone)
                    ),
                    ResourceOffer(
                        id = Uuid.random(),
                        category = Resource.Food,
                        scope = TemporalScope.Indefinite,
                        description = "Lots of rice",
                        timePosted = referenceDateTime.toInstant(timeZone)
                    )
                ),
                resources = setOf(),
                category = Resource.Food
            ),
            onOfferClaim = { },
            onRequestResponse = { },
            onResourceFilterChanged = { },
            onResourceCategoryChanged = { },
            onCreateOffer = { }
        )
    }
}
