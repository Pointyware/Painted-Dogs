package org.pointyware.painteddogs.feature.funds

import kotlinx.coroutines.test.runTest
import org.pointyware.kass.assertions.assertThat
import org.pointyware.kass.assertions.objects.ObjectStatements.isEqualTo
import org.pointyware.kass.assertions.objects.ObjectStatements.isNotNull
import org.pointyware.kass.assertions.result.ResultStatements.isFailure
import org.pointyware.kass.assertions.result.ResultStatements.isSuccess
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.StringArgumentException
import org.pointyware.painteddogs.core.entities.usDollars
import org.pointyware.painteddogs.feature.funds.interactors.CreateFundUseCase
import org.pointyware.painteddogs.feature.funds.interactors.CreateFundUseCaseImpl
import org.pointyware.painteddogs.feature.funds.test.TestFundRepository
import org.pointyware.painteddogs.feature.funds.test.TestFundRepositoryImpl
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@UnitTest
class CreateDonationUseCaseTest {

    private lateinit var fakeRepository: TestFundRepository
    private lateinit var service: CreateFundUseCase
    @BeforeTest
    fun setup() {
        fakeRepository = TestFundRepositoryImpl()
        service = CreateFundUseCaseImpl(fakeRepository)
    }

    @Test
    fun success() = runTest {
        val given = DonationParams(
            title = "Test Fund",
            description = "Test Description",
            targetAmount = 2000L.usDollars()
        )

        val result = service.invoke(given.title, given.description, given.targetAmount)

        assertThat(result, isSuccess())
        with(result.getOrThrow()) {
            assertThat(id, isNotNull())
            assertThat(title, isEqualTo(given.title))
            assertThat(description, isEqualTo(given.description))
            assertThat(target, isEqualTo(given.targetAmount))
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
            assertThat(this::class, isEqualTo(StringArgumentException.LengthArgumentException.AtMost::class))
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
            assertThat(this::class, isEqualTo(StringArgumentException.LengthArgumentException.AtMost::class))
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
            assertThat(this::class, isEqualTo(StringArgumentException.BlankStringArgumentException::class))
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
            assertThat(this::class, isEqualTo(StringArgumentException.BlankStringArgumentException::class))
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

        assertThat(result, isFailure())
        assertThat(result, isEqualTo(fakeResult))
    }
}
