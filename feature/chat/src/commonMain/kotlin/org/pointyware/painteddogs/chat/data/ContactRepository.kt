package org.pointyware.painteddogs.chat.data

import org.pointyware.painteddogs.chat.entities.Contact

interface ContactRepository {
    suspend fun addContact(contact: Contact): Result<Unit>
    suspend fun getContacts(): Result<List<Contact>>
    suspend fun getContactById(id: String): Result<Contact>
    suspend fun setContact(contact: Contact): Result<Unit>
    suspend fun removeContact(id: String): Result<Unit>
}

/**
 *
 */
class ContactRepositoryImpl: ContactRepository {
    override suspend fun addContact(contact: Contact): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getContacts(): Result<List<Contact>> {
        TODO("Not yet implemented")
    }

    override suspend fun getContactById(id: String): Result<Contact> {
        TODO("Not yet implemented")
    }

    override suspend fun setContact(contact: Contact): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun removeContact(id: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}

