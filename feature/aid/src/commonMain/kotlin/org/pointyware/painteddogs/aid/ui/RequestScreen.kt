package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.Res
import org.pointyware.painteddogs.aid.entities.TemporalScope
import org.pointyware.painteddogs.aid.label_submit
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.LocalGeometry

data class RequestScreenState(
    val temporalScope: TemporalScope,
    val description: String
)

@Composable
fun RequestScreen(
    state: RequestScreenState,
    onScopeSelected: (TemporalScope)->Unit,
    onSubmit: ()->Unit
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

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onSubmit
        ) {
            Text(
                text = stringResource(Res.string.label_submit)
            )
        }
    }
}
