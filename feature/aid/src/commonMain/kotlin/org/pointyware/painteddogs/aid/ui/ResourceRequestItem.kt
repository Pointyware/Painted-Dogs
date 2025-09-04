package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import org.pointyware.painteddogs.aid.entities.RequestImage
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.label_provide
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.LocalDateFormat
import org.pointyware.painteddogs.core.ui.design.paddingMedium
import kotlin.time.ExperimentalTime

/**
 *
 */
@OptIn(ExperimentalTime::class)
data class ResourceRequestItemState(
    val request: ResourceRequest
) {
    val description: String get() = request.description

    val timePosted: String
        @Composable
        get() {
            return LocalDateFormat.current.format(request.timePosted)
        }
}

/**
 *
 */
@Composable
fun ResourceRequestItem(
    state: ResourceRequestItemState,
    onProvideResource: ()->Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        tonalElevation = GeometryTokens.dpSmall
    ) {
        Column(
            modifier = Modifier
                .paddingMedium(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(GeometryTokens.dpMedium)
            ) {
                RequestImage()
                ResourceImage(
                    resource = state.request.category
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
                    value = state.request.scope
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
    }
}
