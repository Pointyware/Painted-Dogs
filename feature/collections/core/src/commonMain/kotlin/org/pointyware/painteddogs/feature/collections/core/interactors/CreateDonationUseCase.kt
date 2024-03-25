package org.pointyware.painteddogs.feature.collections.core.interactors

import org.pointyware.painteddogs.core.entities.BlankStringArgumentException
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.NumberArgumentException
import org.pointyware.painteddogs.core.entities.StringArgumentException
import org.pointyware.painteddogs.feature.collections.core.data.FundRepository
import kotlin.coroutines.cancellation.CancellationException

interface CreateDonationUseCase {
    /**
     * @throws IllegalArgumentException if title or description are blank
     */
    @Throws(BlankStringArgumentException::class, NumberArgumentException::class, CancellationException::class)
    suspend fun invoke(title: String, description: String, targetAmount: CurrencyAmount): Result<Fund>
}

class CreateDonationUseCaseImpl(
    private val repository: FundRepository
) : CreateDonationUseCase {

    private val exclusiveMinimum = 0
    private val titleMaxLength = 100

    override suspend fun invoke(title: String, description: String, targetAmount: CurrencyAmount): Result<Fund> {
        when {
            title.isBlank() -> return Result.failure(BlankStringArgumentException("Title"))
            title.length >= titleMaxLength -> return Result.failure(StringArgumentException.LengthArgumentException.AtMost(titleMaxLength, "Title", title.length))
            description.isBlank() -> return Result.failure(BlankStringArgumentException("Description"))
            targetAmount.amount <= exclusiveMinimum -> return Result.failure(NumberArgumentException.AtMost(exclusiveMinimum, "Target Amount", targetAmount.amount))
        }

        return repository.startDonationDrive(title, description, targetAmount)
    }
}
