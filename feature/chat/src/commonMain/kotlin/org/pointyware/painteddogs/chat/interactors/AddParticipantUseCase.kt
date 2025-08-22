package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.data.ContactRepository
import org.pointyware.painteddogs.chat.entities.Participant

/**
 *
 */
class AddParticipantUseCase(
    private val contactRepository: ContactRepository
) {
    suspend fun invoke(id: String, list: List<Participant>): Result<List<Participant>> {
        return contactRepository.getContactById(id)
            .mapCatching {
                val newParticipant = Participant(it.username, userId = it.id)
                if (list.contains(newParticipant)) throw IllegalArgumentException("Participant already in list")
                list + newParticipant
            }
    }
}
