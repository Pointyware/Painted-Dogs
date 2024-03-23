package org.pointyware.painteddogs.feature.collections.core.interactors

import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.feature.collections.core.data.FundRepository

class SearchCollectionsUseCase(
    private val collectionRepository: FundRepository
) {
    suspend operator fun invoke(query: String): Result<List<Fund>> {
        return collectionRepository.search(query)
    }
}
