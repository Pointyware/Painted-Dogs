package org.pointyware.painteddogs.aid.interactors

import org.pointyware.painteddogs.aid.data.AidRepository
import org.pointyware.painteddogs.aid.entities.ResourceOffer

/**
 *
 */
class CreateOfferUseCase(
    private val aidRepository: AidRepository
) {
    suspend fun invoke(title: String, description: String, offer: ResourceOffer): Result<Unit> {
        TODO()
    }
}
