package org.pointyware.painteddogs.aid.ui

   import androidx.compose.foundation.layout.Column
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
            setOf(TemporalScope.Limited, TemporalScope.Schedule),
            setOf(TemporalScope.Schedule, TemporalScope.Event),
            TemporalScope.entries.toSet()
        )
}

@Preview
@Composable
private fun TemporalSelectorPreview(
    @PreviewParameter(TemporalScopeSetProvider::class) filter: Set<TemporalScope>
) {
    Column {
        SingleTemporalSelector(
            value = filter.first(),
            onScopeSelected = { },
        )
        MultiTemporalSelector(
            values = filter,
            onScopeSelected = { }
        )
    }
}
