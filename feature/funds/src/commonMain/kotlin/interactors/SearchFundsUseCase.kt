package org.pointyware.painteddogs.feature.funds.interactors

import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.feature.funds.data.FundRepository

/**
 * Use case for searching donations/collections.
 */
interface SearchCollectionsUseCase {
    suspend operator fun invoke(query: String): Result<List<Fund>>
}

/**
 *
 */
class SearchCollectionsUseCaseImpl(
    private val collectionRepository: FundRepository
) : SearchCollectionsUseCase {
    override suspend operator fun invoke(query: String): Result<List<Fund>> {
        return collectionRepository.search(query)
    }
}
