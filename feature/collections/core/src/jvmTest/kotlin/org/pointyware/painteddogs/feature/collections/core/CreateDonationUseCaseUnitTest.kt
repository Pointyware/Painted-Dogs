package org.pointyware.painteddogs.feature.collections.core

import com.google.common.truth.Truth.assertThat
import com.google.common.truth.TruthJUnit.assume
import io.mockk.coVerify
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.experimental.theories.DataPoint
import org.junit.experimental.theories.Theories
import org.junit.experimental.theories.Theory
import org.junit.runner.RunWith
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.data.CollectionRepository
import org.pointyware.painteddogs.feature.collections.core.interactors.CreateDonationUseCase
import org.pointyware.painteddogs.feature.collections.core.test.TestCollectionRepository

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

    companion object {
        @JvmField
        @DataPoint
        public var successCase =
            DonationParams.Builder()
                .title("Help Support Local Animal Shelter")
                .description("Donations needed for food and supplies")
                .targetAmount(CurrencyAmount(5000.0))
                .build()

        @JvmField
        @DataPoint
        public var failureCase =
            DonationParams.Builder()
                .title("Help Support Local Animal Shelter")
                .description("Donations needed for food and supplies")
                .targetAmount(CurrencyAmount(-5000.0))
                .build()
    }

    private lateinit var mockRepository: CollectionRepository
    private lateinit var service: CreateDonationUseCase
    @Before
    fun setup() {
        mockRepository = spyk<TestCollectionRepository>()
        service = CreateDonationUseCase(mockRepository)
    }

    @Theory
    fun `createDonationCollection - success`(given: DonationParams) {
        /*
        Given a title, description, and target amount
         */
        assume().that(given.targetAmount.amount).isGreaterThan(0)

        /*
        When the use case is invoked
         */
        val result = runBlocking { service.invoke(given.title, given.description, given.targetAmount).getOrThrow() }

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
        assertThat(result.type).isEqualTo(CollectionType.CROWDFUNDING)
        assertThat(result.title).isEqualTo(given.title)
        assertThat(result.description).isEqualTo(given.description)
        assertThat(result.targetAmount).isEqualTo(given.targetAmount)

        coVerify { mockRepository.startDonationDrive(given.title, given.description, given.targetAmount) }
    }

    @Theory
    fun `createDonationCollection - invalid target amount`(given: DonationParams) {
        /*
        Given a title, description, and target amount
         */
        assume().that(given.targetAmount.amount).isAtMost(0)

        /*
        When the use case is invoked
         */
        val result = runBlocking { service.invoke(given.title, given.description, given.targetAmount).getOrNull() }

        /*
        Then a new donation collection is not created and saved
            1. The result is null
            2. The collection is not saved to the repository
         */
        assertThat(result).isNull()

        coVerify(exactly = 0) { mockRepository.startDonationDrive(given.title, given.description, given.targetAmount) }
    }
}
