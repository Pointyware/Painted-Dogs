package org.pointyware.painteddogs.aid.interactors

import org.pointyware.painteddogs.aid.data.AidRepository
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceOffer
import org.pointyware.painteddogs.aid.entities.TemporalScope

/**
 *
 */
class CreateOfferUseCase(
    private val aidRepository: AidRepository
) {
    suspend fun invoke(description: String, category: Resource, scope: TemporalScope): Result<ResourceOffer> {
        return aidRepository.createOffer(description, category, scope)
    }
}
