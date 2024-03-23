package org.pointyware.painteddogs.feature.collections.core

import kotlinx.coroutines.runBlocking
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.usDollars
import org.pointyware.painteddogs.feature.collections.core.data.FundRepository
import org.pointyware.painteddogs.feature.collections.core.interactors.CreateDonationUseCase
import org.pointyware.painteddogs.feature.collections.core.test.TestFundRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class CreateDonationUseCaseTest {

    private lateinit var fakeRepository: FundRepository
    private lateinit var service: CreateDonationUseCase
    @BeforeTest
    fun setup() {
        fakeRepository = TestFundRepository()
        service = CreateDonationUseCase(fakeRepository)
    }
    @Test
    fun `title validation - too long`() { // Changed test name for accuracy
        val longTitle = generateString(101) // Ensure a title exceeding the 100-character limit
        val given = DonationParams(
            title = longTitle,
            description = "Valid description",
            targetAmount = 2000L.usDollars()
        )

        /*
        When the use case is invoked
         */
        val result = runBlocking { service.invoke(given.title, given.description, given.targetAmount) }

        assertTrue(result.isFailure)
    }
}
