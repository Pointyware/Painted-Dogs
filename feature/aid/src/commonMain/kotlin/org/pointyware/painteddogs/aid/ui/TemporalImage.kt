package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.aid.entities.TemporalScope

@Composable
fun TemporalImage(
    value: TemporalScope,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        imageVector = vectorForScope(value),
        contentDescription = stringForScope(value)
    )
}
