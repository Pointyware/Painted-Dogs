package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.Res
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.TemporalScope
import org.pointyware.painteddogs.aid.label_description
import org.pointyware.painteddogs.aid.label_submit
import org.pointyware.painteddogs.core.ui.design.LocalGeometry
import org.pointyware.painteddogs.ui.label_unknown_error
import org.pointyware.painteddogs.ui.Res as UiRes

data class OfferScreenState(
    val description: String,
    val category: Resource,
    val scope: TemporalScope,
)

@Composable
fun OfferScreen(
    state: OfferScreenState,
    onSelectTemporalScope: (TemporalScope)->Unit,
    onSelectResourceCategory: (Resource)->Unit,
    onChangeDescription: (String)->Unit,
    onClearError: ()->Unit,
    onSubmit: ()->Unit,
    throwable: Throwable? = null,
) {
    Column(
        modifier = Modifier
    ) {
        SingleTemporalSelector(
            value = state.scope,
            modifier = Modifier,
            onScopeSelected = onSelectTemporalScope
        )
        SingleResourceSelector(
            value = state.category,
            modifier = Modifier,
            onResourceSelected = onSelectResourceCategory
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

        throwable?.let {
            Dialog(
                onDismissRequest = onClearError,
            ) {
                Surface(
                    modifier  = Modifier.padding(LocalGeometry.current.paddingMedium)
                ) {
                    Text(
                        text = it.message ?: stringResource(UiRes.string.label_unknown_error)
                    )
                }
            }
        }

        Button(
            onClick = onSubmit
        ) {
            Text(
                text = stringResource(Res.string.label_submit)
            )
        }
    }
}
