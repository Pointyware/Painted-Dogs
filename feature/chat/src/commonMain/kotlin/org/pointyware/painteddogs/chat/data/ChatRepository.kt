package org.pointyware.painteddogs.chat.data

import org.pointyware.painteddogs.chat.entities.Contact

interface ChatRepository {
    suspend fun createChat(title: String, participantList: List<Contact>): Result<String>
    suspend fun getChat(id: String): Result<ChatLog>
}


class ChatRepositoryImpl(): ChatRepository {
    override suspend fun createChat(
        title: String,
        participantList: List<Contact>
    ): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getChat(id: String): Result<ChatLog> {
        TODO("Not yet implemented")
    }
}
