package org.pointyware.painteddogs.feature.collections.core

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.data.CollectionRepository
import org.pointyware.painteddogs.feature.collections.core.data.OfflineFirstCollectionRepository
import org.pointyware.painteddogs.feature.collections.core.interactors.CreateDonationUseCase
import org.pointyware.painteddogs.feature.collections.core.local.CollectionCache
import org.pointyware.painteddogs.feature.collections.core.local.InMemoryCollectionCache
import org.pointyware.painteddogs.feature.collections.core.remote.CollectionApi
import org.pointyware.painteddogs.feature.collections.core.remote.TestCollectionApi
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 *
 */
class CreateDonationUseCaseIntegrationTest {

    private lateinit var collectionApi: CollectionApi
    private lateinit var collectionCache: CollectionCache
    private lateinit var repository: CollectionRepository
    private lateinit var service: CreateDonationUseCase
    @BeforeTest
    fun setup() {
        collectionApi = TestCollectionApi()
        collectionCache = InMemoryCollectionCache()
        repository = OfflineFirstCollectionRepository(
            localDataSource = collectionCache,
            remoteDataSource = collectionApi
        )
        service = CreateDonationUseCase(repository)
    }

    @Test
    fun `createDonationCollection - success`() {
        /*
        Given a title, description, and target amount
         */
        val title = "Help Support Local Animal Shelter"
        val description = "Donations needed for food and supplies"
        val targetAmount = CurrencyAmount(5000.0)

        /*
        When the use case is invoked
         */
        val result = runBlocking { service.invoke(title, description, targetAmount).getOrThrow() }

        /*
        Then a new donation collection is created and saved
            1. The collection has a unique id
            2. The collection has the correct type
            3. The collection has the correct title
            4. The collection has the correct description
            5. The collection has the correct target amount
            6. A donation collection is started through the repository
         */
        assertThat(result.id).isNotIn(setOf(Uuid.nil(), Uuid.max()))
        assertThat(result.type).isEqualTo(CollectionType.CROWDFUNDING)
        assertThat(result.title).isEqualTo(title)
        assertThat(result.description).isEqualTo(description)
        assertThat(result.targetAmount).isEqualTo(targetAmount)
    }
}
