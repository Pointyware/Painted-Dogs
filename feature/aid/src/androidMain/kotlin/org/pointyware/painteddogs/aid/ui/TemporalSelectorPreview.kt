package org.pointyware.painteddogs.aid.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.pointyware.painteddogs.aid.entities.TemporalScope

class TemporalScopeSetProvider: PreviewParameterProvider<Set<TemporalScope>> {
    override val values: Sequence<Set<TemporalScope>>
        get() = sequenceOf(
            setOf(TemporalScope.Indefinite),
            setOf(TemporalScope.Limited),
            TemporalScope.entries.toSet()
        )
}

@Preview
@Composable
private fun TemporalSelectorPreview(
    @PreviewParameter(TemporalScopeSetProvider::class) filter: Set<TemporalScope>
) {
    TemporalSelector(
        value = filter.first(),
        onScopeSelected = { },
    )
}
