package org.pointyware.painteddogs.entities

/**
 *
 */
sealed class Measurement {

    /**
     * The base [Measurement] used for conversions.
     */
    abstract val commonBase: Measurement

    /**
     * The number of the given units per measurement base.
     * This is exactly 1 for [commonBase]#[unitsPerBase].
     */
    abstract val unitsPerBase: Double
    fun toCommon(value: Double): Double {
        return value / unitsPerBase
    }

    /**
     *
     */
    abstract class Length(
        val unitsPerMeter: Double
    ): Measurement() {
        override val commonBase: Measurement
            get() = Meters
        override val unitsPerBase: Double
            get() = unitsPerMeter

        data object Centimeters: Length(1e1)
        data object Meters: Length(1e0)
        data object Kilometers: Length(1e-3)
        data object Feet: Length(3.28084)
        data object Inches: Length(39.3701)
    }

    /**
     *
     */
    abstract class Mass(
        val unitsPerGram: Double
    ): Measurement() {
        override val commonBase: Measurement
            get() = Grams
        override val unitsPerBase: Double
            get() = unitsPerGram

        data object Grams: Mass(1e0)
        data object Kilograms: Mass(1e-3)
        data object Pounds: Mass(.00220462)
    }

    /**
     *
     */
    abstract class Force(
        val unitsPerNewton: Double
    ): Measurement() {
        override val commonBase: Measurement
            get() = Newtons
        override val unitsPerBase: Double
            get() = unitsPerNewton

        data object Newtons: Force(1e0)
        data object Pounds: Force(0.224809)
    }
}

/**
 * Consider alternative naming:
 * Measurement of Volume `Measurement<Volume.Oz>, Measurement<Volume.Gallon>`
 * Measurement of Mass `Measurement<Mass.Oz>, Measurement<Mass.Pound>`
 * Measurement of Energy `Measurement<Energy.Calorie>`
 * Measurement of Count `Measurement<Count>`
 */
sealed interface Quantity<M:Measurement> {
    val value: Double
    val measurement: M
}

/**
 *
 */
private data class SimpleQuantity<M:Measurement>(
    override val value: Double,
    override val measurement: M
): Quantity<M>

fun Double.meters(): Quantity<Measurement.Length> {
    return SimpleQuantity(
        this,
        Measurement.Length.Meters
    )
}
