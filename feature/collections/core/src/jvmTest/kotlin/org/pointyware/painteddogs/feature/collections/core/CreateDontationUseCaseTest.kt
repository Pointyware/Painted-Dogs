package org.pointyware.painteddogs.feature.collections.core

import io.mockk.spyk
import io.mockk.verify
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.feature.collections.core.test.TestCollectionRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 *
 */
class CreateDonationUseCaseTest {

    private lateinit var mockRepository: CollectionRepository
    private lateinit var service: CreateDonationUseCase
    @BeforeTest
    fun setup() {
        mockRepository = spyk<TestCollectionRepository>()
        service = CreateDonationUseCase(mockRepository)
    }

    @Test
    fun `createDonationCollection - success`() {
        /*
        Given a title, description, and target amount
         */
        val title = "Help Support Local Animal Shelter"
        val description = "Donations needed for food and supplies"
        val targetAmount = 5000.0

        /*
        When the use case is invoked
         */
        val result = service.invoke(title, description, targetAmount)

        /*
        Then a new donation collection is created and saved
            1. The collection has a unique id
            2. The collection has the correct type
            3. The collection has the correct title
            4. The collection has the correct description
            5. The collection has the correct target amount
            6. The collection is saved to the repository
         */
        assertEquals(CollectionType.DONATION, result.type)
        assertEquals(title, result.title)
        assertEquals(description, result.description)
        assertEquals(CurrencyAmount(targetAmount), result.targetAmount)
        assertNotNull(result.id)

        verify { mockRepository.startDonationDrive(title, description, targetAmount) }
    }
}
