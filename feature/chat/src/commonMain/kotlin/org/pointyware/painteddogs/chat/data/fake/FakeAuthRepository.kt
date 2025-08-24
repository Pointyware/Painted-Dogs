package org.pointyware.painteddogs.chat.data.fake

import org.pointyware.painteddogs.chat.data.AuthRepository
import org.pointyware.painteddogs.chat.entities.Contact
import org.pointyware.painteddogs.chat.viewmodels.ContactDummyData

class FakeAuthRepository: AuthRepository {
    override val currentUser: Contact
        get() = ContactDummyData.userTaush
}
