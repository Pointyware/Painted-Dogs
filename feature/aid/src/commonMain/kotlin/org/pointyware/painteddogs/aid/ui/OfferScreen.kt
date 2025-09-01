package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.Res
import org.pointyware.painteddogs.aid.entities.TemporalScope
import org.pointyware.painteddogs.aid.label_description
import org.pointyware.painteddogs.aid.label_submit

data class OfferScreenState(
    val description: String,
    val scope: TemporalScope,
)

@Composable
fun OfferScreen(
    state: OfferScreenState,
    onSelectTemporalScope: (TemporalScope)->Unit,
    onChangeDescription: (String)->Unit,
    onSubmit: ()->Unit
) {
    Column(
        modifier = Modifier
    ) {
        SingleTemporalSelector(
            value = state.scope,
            modifier = Modifier,
            onScopeSelected = onSelectTemporalScope
        )

        TextField(
            modifier = Modifier,
            value = state.description,
            onValueChange = onChangeDescription,
            label = {
                Text(
                    text = stringResource(Res.string.label_description)
                )
            }
        )

        Button(
            onClick = onSubmit
        ) {
            Text(
                text = stringResource(Res.string.label_submit)
            )
        }
    }
}
