package org.pointyware.painteddogs.core.ui.org.pointyware.painteddogs.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.entities.RealUnit
import org.pointyware.painteddogs.ui.Res
import org.pointyware.painteddogs.ui.label_unit_force_newtons
import org.pointyware.painteddogs.ui.label_unit_force_pounds
import org.pointyware.painteddogs.ui.label_unit_length_centimeters
import org.pointyware.painteddogs.ui.label_unit_length_feet
import org.pointyware.painteddogs.ui.label_unit_length_inches
import org.pointyware.painteddogs.ui.label_unit_length_kilometers
import org.pointyware.painteddogs.ui.label_unit_length_meters
import org.pointyware.painteddogs.ui.label_unit_mass_grams
import org.pointyware.painteddogs.ui.label_unit_mass_kilograms
import org.pointyware.painteddogs.ui.label_unit_mass_pounds
import org.pointyware.painteddogs.ui.label_unit_unknown

/**
 *
 */
@Composable
fun unitString(unit: RealUnit): String {
    return when (unit) {
        RealUnit.Length.Centimeters -> stringResource(Res.string.label_unit_length_centimeters)
        RealUnit.Length.Meters -> stringResource(Res.string.label_unit_length_meters)
        RealUnit.Length.Kilometers -> stringResource(Res.string.label_unit_length_kilometers)
        RealUnit.Length.Feet -> stringResource(Res.string.label_unit_length_feet)
        RealUnit.Length.Inches -> stringResource(Res.string.label_unit_length_inches)

        RealUnit.Mass.Grams -> stringResource(Res.string.label_unit_mass_grams)
        RealUnit.Mass.Kilograms -> stringResource(Res.string.label_unit_mass_kilograms)
        RealUnit.Mass.Pounds -> stringResource(Res.string.label_unit_mass_pounds)

        RealUnit.Force.Pounds -> stringResource(Res.string.label_unit_force_pounds)
        RealUnit.Force.Newtons -> stringResource(Res.string.label_unit_force_newtons)

        else -> stringResource(Res.string.label_unit_unknown)
    }
}

/**
 *
 */
@Composable
fun UnitView(
    unit: RealUnit,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.labelMedium
) {
    Text(
        modifier = modifier,
        text = unitString(unit = unit),
        style = style
    )
}
