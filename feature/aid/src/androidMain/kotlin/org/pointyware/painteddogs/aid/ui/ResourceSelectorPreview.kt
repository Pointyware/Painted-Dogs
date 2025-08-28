package org.pointyware.painteddogs.aid.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.pointyware.painteddogs.aid.entities.Resource

class ResourceSetProvider: PreviewParameterProvider<Set<Resource>> {
    override val values: Sequence<Set<Resource>>
        get() = sequenceOf(
            setOf(Resource.Food),
            setOf(Resource.Protection),
            Resource.entries.toSet()
        )
}

@Preview
@Composable
private fun ResourceSelectorPreview(
    @PreviewParameter(ResourceSetProvider::class) filter: Set<Resource>
) {
    SingleResourceSelector(
        value = filter.first(),
        onResourceSelected = { },
    )
}
