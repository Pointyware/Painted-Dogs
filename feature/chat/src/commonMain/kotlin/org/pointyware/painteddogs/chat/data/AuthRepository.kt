package org.pointyware.painteddogs.chat.data

import org.pointyware.painteddogs.chat.entities.Contact

interface AuthRepository {

    val currentUser: Contact
}

class AuthRepositoryImpl(): AuthRepository {

    override val currentUser: Contact
        get() = TODO("Not yet implemented")
}
