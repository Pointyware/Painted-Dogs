package org.pointyware.painteddogs.entities

/**
 * Consider alternative naming:
 * Measurement of Volume `Measurement<Volume.Oz>, Measurement<Volume.Gallon>`
 * Measurement of Mass `Measurement<Mass.Oz>, Measurement<Mass.Pound>`
 * Measurement of Energy `Measurement<Energy.Calorie>`
 * Measurement of Count `Measurement<Count>`
 */
sealed interface Quantity<M:RealUnit> {
    val value: Double
    val measurement: M
}

/**
 *
 */
private data class SimpleQuantity<M:RealUnit>(
    override val value: Double,
    override val measurement: M
): Quantity<M>

fun Double.meters(): Quantity<RealUnit.Length> {
    return SimpleQuantity(
        this,
        RealUnit.Length.Meters
    )
}

fun Double.pounds(): Quantity<RealUnit.Mass.Pounds> {
    return SimpleQuantity(
        this,
        RealUnit.Mass.Pounds
    )
}
