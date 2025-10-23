package org.pointyware.painteddogs.core.ui.org.pointyware.painteddogs.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import org.pointyware.painteddogs.entities.RealUnit

class UnitCollectionPreviewParameterProvider :
    CollectionPreviewParameterProvider<RealUnit>(listOf(
        RealUnit.Length.Centimeters,
        RealUnit.Length.Meters,
        RealUnit.Length.Feet,

        RealUnit.Mass.Grams,
        RealUnit.Mass.Kilograms,
        RealUnit.Mass.Pounds,
    ))

@Preview
@Composable
private fun UnitDropdownPreview(
    @PreviewParameter(UnitCollectionPreviewParameterProvider::class) unit: RealUnit
) {
    UnitDropdown(
        value = unit,
        onSelectUnit = {

        }
    )
}
