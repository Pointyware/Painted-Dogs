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
    fun invoke(title: String, description: String, targetAmount: CurrencyAmount): Collection {
        if (title.isBlank() || description.isBlank()) {
            throw IllegalArgumentException("Title and description are required")
        }

        return repository.startDonationDrive(title, description, targetAmount).getOrNull()
            ?: throw IllegalStateException("Failed to start donation drive")
    }
}
