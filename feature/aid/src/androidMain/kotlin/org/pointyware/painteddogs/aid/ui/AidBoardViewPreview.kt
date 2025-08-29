package org.pointyware.painteddogs.aid.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.common.later
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
@Preview
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
    val referenceDate = referenceDateTime.date
    val referenceTime = referenceDateTime.time
    val nextDateTime = referenceDateTime.later(hour = 1, minute = 5)

    PaintedDogsTheme {
        ExchangeBoardScreen(
            state = ExchangeBoardScreenState(
                posts = listOf(
                    ResourceOffer(
                        category = Resource.Food,
                        description = "Lots of rice",
                        timePosted = referenceDateTime.toInstant(timeZone)
                    ),
                    ResourceRequest(
                        category = Resource.Protection,
                        description = "Escort to School",
                        timePosted = nextDateTime.toInstant(timeZone)
                    ),

                    ),
                resources = setOf()
            ),
            onOfferClaim = { },
            onRequestResponse = { },
            onResourceFilterChanged = { },
        )
    }
}
