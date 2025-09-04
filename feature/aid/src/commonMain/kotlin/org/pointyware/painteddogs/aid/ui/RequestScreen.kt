package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.Res
import org.pointyware.painteddogs.aid.entities.TemporalScope
import org.pointyware.painteddogs.aid.label_description
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.LocalGeometry
import org.pointyware.painteddogs.core.ui.org.pointyware.painteddogs.ui.ErrorDialog
import org.pointyware.painteddogs.ui.label_submit
import org.pointyware.painteddogs.ui.Res as UiRes

data class RequestScreenState(
    val temporalScope: TemporalScope,
    val description: String
)

@Composable
fun RequestScreen(
    state: RequestScreenState,
    throwable: Throwable?,
    onScopeSelected: (TemporalScope)->Unit,
    onChangeDescription: (String)->Unit,
    onSubmit: ()->Unit,
    onClearError: ()->Unit,
) {
    Column(
        modifier = Modifier
            .padding(LocalGeometry.current.paddingSmall)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(GeometryTokens.dpMedium),
    ) {
        SingleTemporalSelector(
            value = state.temporalScope,
            onScopeSelected = onScopeSelected,
            modifier = Modifier.fillMaxWidth()
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
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onSubmit
        ) {
            Text(
                text = stringResource(UiRes.string.label_submit)
            )
        }

        ErrorDialog(
            throwable = throwable,
            onDismissRequest = onClearError
        )
    }
}
