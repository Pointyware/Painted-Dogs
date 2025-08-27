package org.pointyware.painteddogs.aid.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceExchange

data class AidBoardViewPreviewState(
    override val posts: List<ResourceExchange>,
    override val resources: Set<Resource>
): AidBoardViewState {
}

@Preview
@Composable
private fun AidBoardViewPreview() {
    AidBoardView(
        state = AidBoardViewPreviewState(
            posts = listOf(

            ),
            resources = setOf()
        ),
        onOfferClaim = { },
        onRequestResponse = { },
        modifier = Modifier
    )
}
