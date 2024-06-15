package org.pointyware.painteddogs.feature.funds.interactors

import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.NumberArgumentException
import org.pointyware.painteddogs.core.entities.StringArgumentException
import org.pointyware.painteddogs.feature.funds.data.FundRepository
import kotlin.coroutines.cancellation.CancellationException

interface CreateFundUseCase {
    /**
     * @throws IllegalArgumentException if title or description are blank
     */
    @Throws(StringArgumentException.BlankStringArgumentException::class, NumberArgumentException::class, CancellationException::class)
    suspend fun invoke(title: String, description: String, targetAmount: CurrencyAmount): Result<Fund>
}

class CreateFundUseCaseImpl(
    private val repository: FundRepository
) : CreateFundUseCase {

    private val exclusiveMinimum = 0
    private val titleMaxLength = 100
    private val descriptionMaxLength = 1000

    override suspend fun invoke(title: String, description: String, targetAmount: CurrencyAmount): Result<Fund> {
        when {
            title.isBlank() -> return Result.failure(StringArgumentException.BlankStringArgumentException("Title"))
            title.length >= titleMaxLength -> return Result.failure(StringArgumentException.LengthArgumentException.AtMost(titleMaxLength, "Title", title.length))
            description.isBlank() -> return Result.failure(StringArgumentException.BlankStringArgumentException("Description"))
            description.length >= descriptionMaxLength -> return Result.failure(StringArgumentException.LengthArgumentException.AtMost(descriptionMaxLength, "Description", description.length))
            targetAmount.amount <= exclusiveMinimum -> return Result.failure(NumberArgumentException.AtMost(exclusiveMinimum, "Target Amount", targetAmount.amount))
        }

        return repository.startDonationDrive(title, description, targetAmount)
    }
}
