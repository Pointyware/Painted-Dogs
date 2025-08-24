package org.pointyware.painteddogs.chat.data

import org.pointyware.painteddogs.chat.entities.Contact

interface ChatRepository {
    suspend fun createChat(title: String, participantList: List<Contact>): Result<String>
    suspend fun getChat(id: String): Result<ChatHeader>

    /**
     * Returns a list of [ChatHeader]s describing the users chat history. The list
     * is sorted with the most recent chats first.
     */
    suspend fun getChatList(): Result<List<ChatHeader>>
    suspend fun getExcerpt(id: String): Result<String>
}


class ChatRepositoryImpl(): ChatRepository {
    override suspend fun createChat(
        title: String,
        participantList: List<Contact>
    ): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getChat(id: String): Result<ChatHeader> {
        TODO("Not yet implemented")
    }

    override suspend fun getChatList(): Result<List<ChatHeader>> {
        TODO("Not yet implemented")
    }

    override suspend fun getExcerpt(id: String): Result<String> {
        TODO("Not yet implemented")
    }
}
