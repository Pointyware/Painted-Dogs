package org.pointyware.painteddogs.chat.data.fake

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import org.pointyware.painteddogs.chat.data.ChatHeader
import org.pointyware.painteddogs.chat.data.ChatLog
import org.pointyware.painteddogs.chat.data.ChatRepository
import org.pointyware.painteddogs.chat.entities.Contact
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class FakeChatRepository(
    private val dataScope: CoroutineScope
): ChatRepository {

    val chatList = mutableListOf<ChatLog>()

    override suspend fun createChat(
        title: String,
        participantList: List<Contact>
    ): Result<String> = withContext(dataScope.coroutineContext) {
        val id = Uuid.random().toString()
        val newChat = ChatLog(id, title, participantList, messages = emptyList())
        chatList += newChat
        Result.success(id)
    }

    override suspend fun getChat(id: String): Result<ChatHeader> {
        return chatList.find { it.id == id }?.let {
            Result.success(ChatHeader(it.id, it.title, it.participants.map { it.id} ))
        } ?: Result.failure(IllegalArgumentException("No chat log with id: $id"))
    }

    override suspend fun getChatList(): Result<List<ChatHeader>> {
        val list = chatList.map { log ->
            ChatHeader(log.id, log.title, log.participants.map { it.id} )
        }
        return Result.success(list)
    }

    override suspend fun getExcerpt(id: String): Result<String> {
        return chatList.find { it.id == id }?.let { log ->
            val excerpt = log.messages.lastOrNull()?.let { message ->
                "${message.sender.username}: ${message.content}"
            } ?: ""
            Result.success(excerpt)
        } ?: Result.failure(IllegalArgumentException("No chat log with id: $id"))
    }
}
