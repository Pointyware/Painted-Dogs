package org.pointyware.painteddogs.feature.collections.core.interactors

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.feature.collections.core.Collection
import org.pointyware.painteddogs.feature.collections.core.data.CollectionRepository

class CreateDonationUseCase(
    private val repository: CollectionRepository
) {

    /**
     * @throws IllegalArgumentException if title or description are blank
     */
    suspend fun invoke(title: String, description: String, targetAmount: CurrencyAmount): Result<Collection> {
        if (title.isBlank() || description.isBlank()) {
            return Result.failure(IllegalArgumentException("Title and description are required"))
        }
        if (targetAmount.amount <= 0) {
            return Result.failure(IllegalArgumentException("Target amount must be greater than 0"))
        }

        return repository.startDonationDrive(title, description, targetAmount)
    }
}
