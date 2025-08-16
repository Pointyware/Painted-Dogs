package org.pointyware.painteddogs.feature.funds

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.pointyware.painteddogs.assertions.assert
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.core.entities.usDollars
import org.pointyware.painteddogs.feature.funds.data.FundRepository
import org.pointyware.painteddogs.feature.funds.data.OfflineFirstFundRepository
import org.pointyware.painteddogs.feature.funds.interactors.CreateFundUseCase
import org.pointyware.painteddogs.feature.funds.interactors.CreateFundUseCaseImpl
import org.pointyware.painteddogs.feature.funds.local.FundCache
import org.pointyware.painteddogs.feature.funds.local.InMemoryFundCache
import org.pointyware.painteddogs.feature.funds.remote.FundApi
import org.pointyware.painteddogs.feature.funds.remote.TestFundApi
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 *
 */
class CreateDonationUseCaseIntegrationTest {

    private lateinit var testDispatcher: TestDispatcher
    private lateinit var testScope: CoroutineScope
    private lateinit var fundApi: FundApi
    private lateinit var fundCache: FundCache
    private lateinit var repository: FundRepository
    private lateinit var service: CreateFundUseCase
    @BeforeTest
    fun setup() {
        testDispatcher = StandardTestDispatcher()
        testScope = CoroutineScope(testDispatcher)
        fundApi = TestFundApi()
        fundCache = InMemoryFundCache()
        repository = OfflineFirstFundRepository(
            localDataSource = fundCache,
            remoteDataSource = fundApi,
            dataScope = testScope
        )
        service = CreateFundUseCaseImpl(repository)
    }

    @Test
    fun `createDonationCollection - success`() = runTest {
        /*
        Given a title, description, and target amount
         */
        val title = "Help Support Local Animal Shelter"
        val description = "Donations needed for food and supplies"
        val targetAmount = 5000L.usDollars()

        /*
        When the use case is invoked
         */
        val result = service.invoke(title, description, targetAmount).getOrThrow()

        /*
        Then a new donation collection is created and saved
            1. The collection has a unique id
            2. The collection has the correct type
            3. The collection has the correct title
            4. The collection has the correct description
            5. The collection has the correct target amount
            6. A donation collection is started through the repository
         */
        assert().that(result.id).isNotIn(setOf(Uuid.nil(), Uuid.max()))
        assert().that(result.title).isEqualTo(title)
        assert().that(result.description).isEqualTo(description)
        assert().that(result.target).isEqualTo(targetAmount)
    }
}
