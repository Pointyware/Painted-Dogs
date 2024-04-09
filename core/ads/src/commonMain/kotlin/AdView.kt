package org.pointyware.painteddogs.core.ads

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

data class AdViewState(
    val adId: String,
    val adImageUrl: String,
    val adClickUrl: String,
)

/**
 * Presents a static ad.
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun AdView(
    state: AdViewState,
    modifier: Modifier = Modifier,
    onClickUrl: (String) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        val image = remember(state.adImageUrl) {
            state.adImageUrl
        }
        Image(
            painter = painterResource(DrawableResource("Test-Ad.svg")),
            contentDescription = "Ad",
            modifier = Modifier.clickable {
                onClickUrl(state.adClickUrl)
            }
        )
    }
}
