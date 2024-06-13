package org.pointyware.painteddogs.core.ads.client

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import painted_dogs.core.ads.client.generated.resources.Res
import painted_dogs.core.ads.client.generated.resources.Test_Ad

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
            painter = painterResource(Res.drawable.Test_Ad),
            contentDescription = "Ad",
            modifier = Modifier.clickable {
                onClickUrl(state.adClickUrl)
            }
        )
    }
}
