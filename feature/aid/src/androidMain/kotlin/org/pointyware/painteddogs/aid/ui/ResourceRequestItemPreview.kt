package org.pointyware.painteddogs.aid.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme
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
