package org.pointyware.painteddogs.entities

abstract class Volume(
    val unitsPerLiter: Double
): RealUnit() {
    override val commonBase: RealUnit
        get() = Liter
    override val unitsPerBase: Double
        get() = unitsPerLiter
}

object Liter: Volume(1e0)
object Milliliter: Volume(1e3)

object Teaspoon: Volume(202.884)
object Tablespoon: Volume(67.628)
object Cup: Volume(4.22675)
object Pint: Volume(2.11338)
object Quart: Volume(1.05669)
object Gallon: Volume(0.264172)
