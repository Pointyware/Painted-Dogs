package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.data.ContactRepository
import org.pointyware.painteddogs.chat.entities.Contact

/**
 *
 */
class AddParticipantUseCase(
    private val contactRepository: ContactRepository
) {
    suspend fun invoke(id: String, list: List<Contact>): Result<List<Contact>> {
        return contactRepository.getContactById(id)
            .mapCatching { newParticipant ->
                if (list.contains(newParticipant)) throw IllegalArgumentException("Participant already in list")
                list + newParticipant
            }
    }
}
