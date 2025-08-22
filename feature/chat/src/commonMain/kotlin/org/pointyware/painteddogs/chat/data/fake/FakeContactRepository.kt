package org.pointyware.painteddogs.chat.data.fake

import org.pointyware.painteddogs.chat.data.ContactRepository
import org.pointyware.painteddogs.chat.entities.Contact

class FakeContactRepository: ContactRepository {

    val contactMap = mutableMapOf<String, Contact>()

    override suspend fun addContact(contact: Contact): Result<Unit> {
        if (contactMap.containsKey(contact.id)) {
            return Result.failure(IllegalArgumentException("Contact already exists for id: ${contact.id}"))
        }
        contactMap.put(contact.id, contact)
        return Result.success(Unit)
    }

    override suspend fun getContactById(id: String): Result<Contact> {
        return contactMap[id]?.let {
            Result.success(it)
        } ?: Result.failure(IllegalArgumentException("No contact for id: $id"))
    }

    override suspend fun setContact(contact: Contact): Result<Unit> {
        contactMap[contact.id] = contact
        return Result.success(Unit)
    }

    override suspend fun removeContact(id: String): Result<Unit> {
        if (contactMap.containsKey(id)) {
            contactMap.remove(id)
            return Result.success(Unit)
        } else {
            return Result.failure(IllegalArgumentException("No contact for id: $id"))
        }
    }
}
