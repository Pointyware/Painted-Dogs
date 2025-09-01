package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.Res
import org.pointyware.painteddogs.aid.entities.OfferImage
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.label_claim
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.LocalDateFormat
import org.pointyware.painteddogs.core.ui.design.LocalGeometry
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
    onClaimOffer: ()->Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        tonalElevation = GeometryTokens.dpSmall
    ) {
        Column(
            modifier = Modifier
                .padding(LocalGeometry.current.paddingMedium),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(GeometryTokens.dpMedium)
            ) {
                OfferImage()
                ResourceImage(
                    resource = state.offer.category
                )
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                    text = state.description,
                    textAlign = TextAlign.End
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(GeometryTokens.dpMedium)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = state.timePosted,
                    style = MaterialTheme.typography.labelSmall
                )
                TemporalImage(
                    value = state.offer.scope
                )
                Button(
                    onClick = onClaimOffer
                ) {
                    Text(
                        text = stringResource(Res.string.label_claim)
                    )
                }
            }
        }
    }
}
