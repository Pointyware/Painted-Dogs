package org.pointyware.painteddogs.core.entities

data class CurrencyAmount constructor(
    val amount: Long,
    val unit: CurrencyUnit
)

enum class CurrencyUnit {
    UsCents,
    UsDollars,
}

fun Long.usCents(): CurrencyAmount {
    return CurrencyAmount(this, CurrencyUnit.UsCents)
}

fun Long.usDollars(): CurrencyAmount {
    return CurrencyAmount(this, CurrencyUnit.UsDollars)
}
