package org.pointyware.painteddogs.aid.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Preview
@Composable
private fun ResourceRequestItemPreview() {
    PaintedDogsTheme {
        ResourceRequestItem(
            state = ResourceRequestItemState(
                request = ResourceRequest(
                    category = Resource.Food,
                    description = "Some food item",
                    timePosted = Clock.System.now()
                )
            ),
            onProvideResource = { },
        )
    }
}
