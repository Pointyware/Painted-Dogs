package org.pointyware.painteddogs.feature.funds

import kotlinx.coroutines.runBlocking
import org.pointyware.painteddogs.assertions.assert
import org.pointyware.painteddogs.assertions.assume
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.core.entities.usDollars
import org.pointyware.painteddogs.feature.funds.data.FundRepository
import org.pointyware.painteddogs.feature.funds.interactors.CreateDonationUseCase
import org.pointyware.painteddogs.feature.funds.interactors.CreateDonationUseCaseImpl
import org.pointyware.painteddogs.feature.funds.test.TestFundRepositoryImpl
import kotlin.jvm.JvmField
import kotlin.test.BeforeTest


fun generateString(length: Int): String {
    return "a".repeat(length)
}
data class DonationParams(
    val title: String,
    val description: String,
    val targetAmount: CurrencyAmount
) {
    class Builder {
        var title: String = ""
        var description: String = ""
        var targetAmount: CurrencyAmount = 0L.usDollars()
        fun title(title: String) = apply { this.title = title }
        fun description(description: String) = apply { this.description = description }
        fun targetAmount(targetAmount: CurrencyAmount) = apply { this.targetAmount = targetAmount }
        fun build() = DonationParams(title, description, targetAmount)
    }
}

/**
 *
 */
//@RunWith(Theories::class)
class CreateDonationUseCaseUnitTest {

    companion object {
        @JvmField
        // @DataPoint
        var successCase =
            DonationParams(
                title = "Help Support Local Animal Shelter",
                description = "Donations needed for food and supplies",
                targetAmount = 5000L.usDollars()
            )
        @JvmField
        // @DataPoint
        var negativeTargetAmountCase =
            DonationParams(
                title = "Help Support Local Animal Shelter",
                description = "Donations needed for food and supplies",
                targetAmount = (-5000L).usDollars()
            )
        @JvmField
        // @DataPoint
        var shortTitleCase =
            DonationParams(
                title = "Help Support Local Animal Shelter",
                description = "Donations needed for food and supplies",
                targetAmount = (-5000L).usDollars()
            )
        @JvmField
        // @DataPoint
        var longTitleCase =
            DonationParams(
                title = "Help Support Local Animal Shelter",
                description = "Donations needed for food and supplies",
                targetAmount = (-5000L).usDollars()
            )
    }

    private lateinit var fakeRepository: FundRepository
    private lateinit var service: CreateDonationUseCase
    @BeforeTest
    fun setup() {
        fakeRepository = TestFundRepositoryImpl()
        service = CreateDonationUseCaseImpl(fakeRepository)
    }

    // @Theory
    fun `createDonationCollection - success`(given: DonationParams) {
        /*
        Given a title, description, and target amount
         */
        assume().that(given.targetAmount.amount).isGreaterThan(0L)

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
        assert().that(result.id).isNotIn(setOf(Uuid.nil(), Uuid.max()))
        assert().that(result.title).isEqualTo(given.title)
        assert().that(result.description).isEqualTo(given.description)
        assert().that(result.target).isEqualTo(given.targetAmount)
    }

    // @Theory
    fun `createDonationCollection - invalid target amount`(given: DonationParams) {
        /*
        Given a title, description, and target amount
         */
        assume().that(given.targetAmount.amount).isAtMost(0L)

        /*
        When the use case is invoked
         */
        val result: Fund? = runBlocking { service.invoke(given.title, given.description, given.targetAmount).getOrNull() }

        /*
        Then a new donation collection is not created and saved
            1. The result is null
            2. The collection is not saved to the repository
         */
        assert().that(result).isNull()
    }
}

class CreateDonationUseCaseParameterizedUnitTest {

    private lateinit var mockRepository: FundRepository
    private lateinit var service: CreateDonationUseCase
    @BeforeTest
    fun setup() {
        mockRepository = TestFundRepositoryImpl()
        service = CreateDonationUseCaseImpl(mockRepository)
    }

    // @ParameterizedTest
//    @ValueSource(ints = [101, 102, 103, 200, 300])
    fun `title validation - too long`(length: Int) {
        val given = DonationParams(
            title = generateString(length),
            description = "Valid description",
            targetAmount = 2000L.usDollars()
        )
        /*
        Given a title, description, and target amount
         */
        assume().that(given.targetAmount.amount).isAtMost(0L)

        /*
        When the use case is invoked
         */
        val result = runBlocking { service.invoke(given.title, given.description, given.targetAmount) }

        /*
        Then a new donation collection is not created and saved
            1. The result is a failure
            2. The collection is not saved to the repository
         */
        assert().that(result).isFailure()
    }

    // @ParameterizedTest
//    @ValueSource(ints = [0, 4, 5])
    fun `title validation - too short`(length: Int) {
        val given = DonationParams(
            title = generateString(length),
            description = "Valid description",
            targetAmount = 2000L.usDollars()
        )
        /*
        Given a title, description, and target amount
         */
        assume().that(given.targetAmount.amount).isAtMost(0L)

        /*
        When the use case is invoked
         */
        val result = runBlocking { service.invoke(given.title, given.description, given.targetAmount) }

        /*
        Then a new donation collection is not created and saved
            1. The result is a failure
            2. The collection is not saved to the repository
         */
        assert().that(result).isFailure()
    }
}
class CreateDonationUseCaseParameterizedMethodUnitTest {

    private lateinit var mockRepository: FundRepository
    private lateinit var service: CreateDonationUseCase
    @BeforeTest
    fun setup() {
        mockRepository = TestFundRepositoryImpl()
        service = CreateDonationUseCaseImpl(mockRepository)
    }

    companion object {
//        @JvmStatic
//        fun dataPoints(): Stream<Arguments> = Stream.of(
//            Arguments.of(0),
//            Arguments.of(4),
//            Arguments.of(5),
//            Arguments.of(101),
//            Arguments.of(102),
//            Arguments.of(103),
//            Arguments.of(200),
//            Arguments.of(300)
//        )
    }

    // @ParameterizedTest
    // @MethodSource("dataPoints")
    fun `title validation - too long`(length: Int) {
        val given = DonationParams(
            title = generateString(length),
            description = "Valid description",
            targetAmount = 2000L.usDollars()
        )
        /*
        Given a title, description, and target amount
         */
        assume().that(given.targetAmount.amount).isAtMost(0L)

        /*
        When the use case is invoked
         */
        val result = runBlocking { service.invoke(given.title, given.description, given.targetAmount) }

        /*
        Then a new donation collection is not created and saved
            1. The result is a failure
            2. The collection is not saved to the repository
         */
        assert().that(result).isFailure()
    }
}
