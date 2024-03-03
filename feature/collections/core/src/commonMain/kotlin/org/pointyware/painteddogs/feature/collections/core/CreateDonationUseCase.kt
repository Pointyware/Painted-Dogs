package org.pointyware.painteddogs.feature.collections.core

class CreateDonationUseCase(
    private val repository: CollectionRepository
) {

    /**
     * @throws IllegalArgumentException if title or description are blank
     */
    fun invoke(title: String, description: String, targetAmount: Double): Collection {
        if (title.isBlank() || description.isBlank()) {
            throw IllegalArgumentException("Title and description are required")
        }

        return repository.startDonationDrive(title, description, targetAmount)
    }
}
