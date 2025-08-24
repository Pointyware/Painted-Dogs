package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.data.ContactRepository
import org.pointyware.painteddogs.chat.entities.Contact

class LoadContactListUseCase(
    private val contactRepository: ContactRepository
) {

    suspend operator fun invoke(): Result<List<Contact>> {
        return contactRepository.getContacts()
    }
}
