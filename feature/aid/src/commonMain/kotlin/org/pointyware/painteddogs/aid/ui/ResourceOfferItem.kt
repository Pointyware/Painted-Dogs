package org.pointyware.painteddogs.aid.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.Res
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.label_provide
import org.pointyware.painteddogs.core.ui.design.LocalDateFormat
import kotlin.time.ExperimentalTime

/**
 *
 */
@OptIn(ExperimentalTime::class)
data class ResourceOfferItemState(
    val offer: ResourceOffer,
) {
    val description: String get() = offer.description

    val timePosted: String
    @Composable
    get() {
        return LocalDateFormat.current.format(offer.timePosted)
    }
}

/**
 *
 */
@Composable
fun ResourceOfferItem(
    state: ResourceOfferItemState,
    onProvideResource: ()->Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
    ) {
        Text(
            text = state.description
        )
        Text(
            text = state.timePosted
        )
        Button(
            onClick = onProvideResource
        ) {
            Text(
                text = stringResource(Res.string.label_provide)
            )
        }
    }
}
