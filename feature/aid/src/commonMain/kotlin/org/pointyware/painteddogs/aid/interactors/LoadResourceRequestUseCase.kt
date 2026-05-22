package org.pointyware.painteddogs.aid.interactors

import org.pointyware.painteddogs.aid.data.AidRepository
import org.pointyware.painteddogs.aid.entities.ResourceRequest
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class LoadResourceRequestUseCase(
    private val resourceRepository: AidRepository
) {

    suspend fun invoke(uuid: Uuid): Result<ResourceRequest> {
         return resourceRepository.getRequest(uuid)
    }
}
