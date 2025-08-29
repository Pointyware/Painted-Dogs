package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.Res
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.label_provide
import org.pointyware.painteddogs.core.ui.design.LocalDateFormat
import org.pointyware.painteddogs.core.ui.design.LocalGeometry
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
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(LocalGeometry.current.paddingMedium),
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
}
