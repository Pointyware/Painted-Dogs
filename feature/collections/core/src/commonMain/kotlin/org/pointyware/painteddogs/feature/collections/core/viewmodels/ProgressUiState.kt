package org.pointyware.painteddogs.feature.collections.core.viewmodels

import org.pointyware.painteddogs.core.entities.CurrencyAmount

/**
 * Represents the current ui state of some progress between two amounts.
 */
data class ProgressUiState(
    /**
     * Localized string representation of the current amount.
     */
    val current: String,
    /**
     * Localized string representation of the target amount.
     */
    val target: String,
    /**
     * Percentage of the target amount that has been reached.
     */
    val percentage: Float
) {
    companion object {
        val EMPTY = ProgressUiState("", "", 0f)

        fun from(current: CurrencyAmount, target: CurrencyAmount): ProgressUiState {
            val currentAmount = current.amount
            val targetAmount = target.amount
            // convert to common currency

            val percentage = if (targetAmount == 0L) 0f else currentAmount / targetAmount
            return ProgressUiState(
                current = currentAmount.toString(),
                target = targetAmount.toString(),
                percentage = percentage.toFloat()
            )
        }
    }
}
