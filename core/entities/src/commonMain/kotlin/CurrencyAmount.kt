package org.pointyware.painteddogs.core.entities

data class CurrencyAmount(
    val amount: Long,
    val unit: CurrencyUnit
) {
    override fun toString(): String {
        return when (unit) {
            CurrencyUnit.UsCents -> "$${amount / 100.0}"
            CurrencyUnit.UsDollars -> "$$amount"
        }
    }
}

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
