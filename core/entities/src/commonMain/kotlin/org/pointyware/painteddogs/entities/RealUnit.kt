package org.pointyware.painteddogs.entities

/*
Real Unit
* Length
    * Inches
    * Centimeters
* Area
    * Inches^2
    * Centimeters^2
* Volume
    * Liters
    * Cups
    * Teaspoon
    * Tablespoon
    * Oz (liquid)
* Mass
    * Grams
    * Kilograms
    * Milligrams
    * Pounds
    * Oz (dry)
* Time
    * Seconds
    * Minutes
    * Hours
    * Days
* Temperature
    * Fahrenheit
    * Celsius
* Count
    * Counts
    * Cans
    * Loaves
    * etc.
 */

/**
 *
 */
sealed class RealUnit {

    /**
     * The base [RealUnit] used for conversions.
     */
    abstract val commonBase: RealUnit

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
    ): RealUnit() {
        override val commonBase: RealUnit
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
    ): RealUnit() {
        override val commonBase: RealUnit
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
    ): RealUnit() {
        override val commonBase: RealUnit
            get() = Newtons
        override val unitsPerBase: Double
            get() = unitsPerNewton

        data object Newtons: Force(1e0)
        data object Pounds: Force(0.224809)
    }
}

