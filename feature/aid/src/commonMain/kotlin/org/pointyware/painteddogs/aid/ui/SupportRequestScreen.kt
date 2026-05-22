package org.pointyware.painteddogs.aid.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class SupportRequestScreenState(
    val request: ResourceRequestItemState,

)

@Composable
fun SupportRequestScreen(
    state: SupportRequestScreenState,
) {
    ResourceRequestItem(
        state = state.request,
        modifier = Modifier,
        onProvideResource = null,
    )

    /*
    TODO: details for supporting a resource request
     */
}
