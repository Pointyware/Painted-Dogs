package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.Res
import org.pointyware.painteddogs.aid.label_loading
import org.pointyware.painteddogs.aid.label_offer
import org.pointyware.painteddogs.aid.viewmodels.OfferInfoUiState

@Composable
fun OfferInfoScreen(
    state: OfferInfoUiState?,
    error: Throwable?
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(Res.string.label_offer),
            style = MaterialTheme.typography.titleSmall
        )
        state?.let {
            Text(
                text = state.offer.description,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "TODO: Scope",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "TODO: TimePosted",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "TODO: Category",
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(
            text = stringResource(Res.string.label_loading),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
