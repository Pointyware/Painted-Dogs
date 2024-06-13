package org.pointyware.painteddogs.feature.funds

import kotlinx.coroutines.test.runTest
import org.pointyware.painteddogs.assertions.assert
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.StringArgumentException
import org.pointyware.painteddogs.core.entities.usDollars
import org.pointyware.painteddogs.feature.funds.interactors.CreateDonationUseCase
import org.pointyware.painteddogs.feature.funds.interactors.CreateDonationUseCaseImpl
import org.pointyware.painteddogs.feature.funds.test.TestFundRepository
import org.pointyware.painteddogs.feature.funds.test.TestFundRepositoryImpl
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@UnitTest
class CreateDonationUseCaseTest {

    private lateinit var fakeRepository: TestFundRepository
    private lateinit var service: CreateDonationUseCase
    @BeforeTest
    fun setup() {
        fakeRepository = TestFundRepositoryImpl()
        service = CreateDonationUseCaseImpl(fakeRepository)
    }

    @Test
    fun success() = runTest {
        val given = DonationParams(
            title = "Test Fund",
            description = "Test Description",
            targetAmount = 2000L.usDollars()
        )

        val result = service.invoke(given.title, given.description, given.targetAmount)

        assert().that(result).isSuccess()
        with(result.getOrThrow()) {
            assert().that(id).isNotNull()
            assert().that(title).isEqualTo(given.title)
            assert().that(description).isEqualTo(given.description)
            assert().that(target).isEqualTo(given.targetAmount)
        }
    }

    @Test
    fun `title validation - too long`() = runTest { // Changed test name for accuracy
        val longTitle = generateString(101) // Ensure a title exceeding the 100-character limit
        val given = DonationParams(
            title = longTitle,
            description = "Valid description",
            targetAmount = 2000L.usDollars()
        )

        val result = service.invoke(given.title, given.description, given.targetAmount)

        assertTrue(result.isFailure)
        with(result.exceptionOrNull()!!) {
            assert().that(this::class).isEqualTo(StringArgumentException.LengthArgumentException.AtMost::class)
        }
    }

    @Test
    fun `description validation - too long`() = runTest {
        val longDescription = generateString(1001) // Ensure a description exceeding the 1000-character limit
        val given = DonationParams(
            title = "Valid title",
            description = longDescription,
            targetAmount = 2000L.usDollars()
        )

        val result = service.invoke(given.title, given.description, given.targetAmount)

        assertTrue(result.isFailure)
        with(result.exceptionOrNull()!!) {
            assert().that(this::class).isEqualTo(StringArgumentException.LengthArgumentException.AtMost::class)
        }
    }

    @Test
    fun `title validation - blank`() = runTest {
        val given = DonationParams(
            title = "",
            description = "Valid description",
            targetAmount = 2000L.usDollars()
        )

        val result = service.invoke(given.title, given.description, given.targetAmount)

        assertTrue(result.isFailure)
        with(result.exceptionOrNull()!!) {
            assert().that(this::class).isEqualTo(StringArgumentException.BlankStringArgumentException::class)
        }
    }

    @Test
    fun `description validation - blank`() = runTest {
        val given = DonationParams(
            title = "Valid title",
            description = "",
            targetAmount = 2000L.usDollars()
        )

        val result = service.invoke(given.title, given.description, given.targetAmount)

        assertTrue(result.isFailure)
        with(result.exceptionOrNull()!!) {
            assert().that(this::class).isEqualTo(StringArgumentException.BlankStringArgumentException::class)
        }
    }

    @Test
    fun `use case passes repo result through`() = runTest {
        val given = DonationParams(
            title = "Test Fund",
            description = "Test Description",
            targetAmount = 2000L.usDollars()
        )
        val fakeResult = Result.failure<Fund>(IllegalArgumentException("Some arbitrary exception - it doesn't matter"))
        fakeRepository.startDonationDriveResponse = fakeResult

        val result = service.invoke(given.title, given.description, given.targetAmount)

        assert().that(result).isFailure()
        assert().that(result).isEqualTo(fakeResult)
    }
}
