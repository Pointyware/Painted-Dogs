package org.pointyware.painteddogs.aid.interactors

import org.pointyware.painteddogs.aid.data.AidRepository
import org.pointyware.painteddogs.aid.entities.ResourceRequest

/**
 * Creates a new [ResourceRequest], delegating to the given [aidRepository].
 */
class CreateRequestUseCase(
    private val aidRepository: AidRepository
) {

    suspend operator fun invoke(draft: RequestDraft): Result<ResourceRequest> {
        return aidRepository.createRequest(draft.description, draft.category, draft.scope)
    }
}
