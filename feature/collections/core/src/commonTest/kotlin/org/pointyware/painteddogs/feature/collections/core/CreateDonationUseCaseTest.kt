package org.pointyware.painteddogs.feature.collections.core

import kotlinx.coroutines.test.runTest
import org.pointyware.painteddogs.core.entities.usDollars
import org.pointyware.painteddogs.feature.collections.core.data.FundRepository
import org.pointyware.painteddogs.feature.collections.core.interactors.CreateDonationUseCase
import org.pointyware.painteddogs.feature.collections.core.interactors.CreateDonationUseCaseImpl
import org.pointyware.painteddogs.feature.collections.core.test.TestFundRepositoryImpl
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@UnitTest
class CreateDonationUseCaseTest {

    private lateinit var fakeRepository: FundRepository
    private lateinit var service: CreateDonationUseCase
    @BeforeTest
    fun setup() {
        fakeRepository = TestFundRepositoryImpl()
        service = CreateDonationUseCaseImpl(fakeRepository)
    }
    @Test
    fun `title validation - too long`() = runTest { // Changed test name for accuracy
        val longTitle = generateString(101) // Ensure a title exceeding the 100-character limit
        val given = DonationParams(
            title = longTitle,
            description = "Valid description",
            targetAmount = 2000L.usDollars()
        )

        /*
        When the use case is invoked
         */
        val result = service.invoke(given.title, given.description, given.targetAmount)

        assertTrue(result.isFailure)
    }
}
