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
import org.pointyware.painteddogs.core.ui.design.LocalDateFormat
import org.pointyware.painteddogs.core.ui.org.pointyware.painteddogs.ui.ErrorDialog
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun OfferInfoScreen(
    state: OfferInfoUiState?,
    error: Throwable?,
    onClearError: ()->Unit,
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
            TemporalImage(
                value = state.offer.scope
            )
            Text(
                text = LocalDateFormat.current.format(state.offer.timePosted),
                style = MaterialTheme.typography.bodySmall
            )
            ResourceImage(state.offer.category)
        }
        Text(
            text = stringResource(Res.string.label_loading),
            style = MaterialTheme.typography.bodySmall
        )

        ErrorDialog(
            throwable = error,
            onDismissRequest = onClearError,
        )
    }
}
