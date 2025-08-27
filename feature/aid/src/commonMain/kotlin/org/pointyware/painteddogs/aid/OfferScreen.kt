package org.pointyware.painteddogs.aid

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.entities.TemporalScope
import org.pointyware.painteddogs.aid.ui.TemporalSelector

data class OfferScreenState(
    val title: String,
    val scope: TemporalScope,
)

@Composable
fun OfferScreen(
    state: OfferScreenState,
    onSelectTemporalScope: (TemporalScope)->Unit,
    onChangeTitle: (String)->Unit,
    onSubmit: ()->Unit
) {
    Column(
        modifier = Modifier
    ) {
        TemporalSelector(
            value = state.scope,
            modifier = Modifier,
            onScopeSelected = onSelectTemporalScope
        )

        TextField(
            modifier = Modifier,
            value = state.title,
            onValueChange = onChangeTitle
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
