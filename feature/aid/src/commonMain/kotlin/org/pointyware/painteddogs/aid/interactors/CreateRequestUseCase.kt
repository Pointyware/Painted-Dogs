package org.pointyware.painteddogs.aid.interactors

import org.pointyware.painteddogs.aid.data.AidRepository
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import org.pointyware.painteddogs.aid.entities.TemporalScope

/**
 * Creates a new [ResourceRequest], delegating to the given [aidRepository].
 */
class CreateRequestUseCase(
    private val aidRepository: AidRepository
) {

    suspend operator fun invoke(description: String, category: Resource, scope: TemporalScope): Result<ResourceRequest> {
        return aidRepository.createRequest(description, category, scope)
    }
}
