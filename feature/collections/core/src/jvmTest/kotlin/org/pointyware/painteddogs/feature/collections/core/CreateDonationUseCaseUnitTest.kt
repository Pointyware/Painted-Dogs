package org.pointyware.painteddogs.feature.collections.core

import com.google.common.truth.Truth.assertThat
import io.mockk.spyk
import io.mockk.verify
import org.junit.experimental.theories.Theories
import org.junit.experimental.theories.Theory
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.junit.runner.RunWith
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.data.CollectionRepository
import org.pointyware.painteddogs.feature.collections.core.interactors.CreateDonationUseCase
import org.pointyware.painteddogs.feature.collections.core.test.TestCollectionRepository
import java.util.stream.Stream
import kotlin.test.BeforeTest
import kotlin.test.Test

data class DonationParams(
    val title: String,
    val description: String,
    val targetAmount: CurrencyAmount
) {
    class Builder {
        var title: String = ""
        var description: String = ""
        var targetAmount: CurrencyAmount = CurrencyAmount(0.0)
        fun title(title: String) = apply { this.title = title }
        fun description(description: String) = apply { this.description = description }
        fun targetAmount(targetAmount: CurrencyAmount) = apply { this.targetAmount = targetAmount }
        fun build() = DonationParams(title, description, targetAmount)
    }
}

interface RepoFactory {
    fun createRepo(): CollectionRepository
}

class TestRepoFactory: RepoFactory {
    override fun createRepo(): CollectionRepository {
        return TestCollectionRepository()
    }
}

/**
 *
 */
@RunWith(Theories::class)
class CreateDonationUseCaseUnitTestOriginal {

    private lateinit var mockRepository: CollectionRepository
    private lateinit var service: CreateDonationUseCase
    @BeforeTest
    fun setup() {
        mockRepository = spyk<TestCollectionRepository>()
        service = CreateDonationUseCase(mockRepository)
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(),
                Arguments.of(),
                Arguments.of(),
                Arguments.of(),
                Arguments.of()
            )
        }
    }

    @Theory
    @ParameterizedTest
    @ValueSource(classes = [])
    @MethodSource("testData")
    fun `createDonationCollection - success`(factory: RepoFactory, given: DonationParams) {
        val given = DonationParams.Builder()
            .title("Help Support Local Animal Shelter")
            .description("Donations needed for food and supplies")
            .targetAmount(CurrencyAmount(5000.0))
            .build()

        /*
        When the use case is invoked
         */
        val result = service.invoke(given.title, given.description, given.targetAmount)

        /*
        Then a new donation collection is created and saved
            1. The collection has a unique id
            2. The collection has the correct type
            3. The collection has the correct title
            4. The collection has the correct description
            5. The collection has the correct target amount
            6. The collection is saved to the repository
         */
        assertThat(result.id).isNotIn(setOf(Uuid.nil(), Uuid.max()))
        assertThat(result.type).isEqualTo(CollectionType.DONATION)
        assertThat(result.title).isEqualTo(given.title)
        assertThat(result.description).isEqualTo(given.description)
        assertThat(result.targetAmount).isEqualTo(given.targetAmount)

        verify { mockRepository.startDonationDrive(given.title, given.description, given.targetAmount) }
    }

    @Test
    fun `createDonationCollection - invalid target amount`() {
        /*
        Given a title, description, and target amount
         */
        val title = "Help Support Local Animal Shelter"
        val description = "Donations needed for food and supplies"
        val targetAmount = CurrencyAmount(-5000.0)

        /*
        When the use case is invoked
         */
        val result = service.invoke(title, description, targetAmount)

        /*
        Then a new donation collection is not created and saved
            1. The result is null
            2. The collection is not saved to the repository
         */
        assertThat(result).isNull()

        verify(exactly = 0) { mockRepository.startDonationDrive(title, description, targetAmount) }
    }
}
