package org.pointyware.painteddogs.chat.data.fake

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import org.pointyware.painteddogs.chat.data.ChatLog
import org.pointyware.painteddogs.chat.data.ChatRepository
import org.pointyware.painteddogs.chat.entities.Participant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class FakeChatRepository(
    private val dataScope: CoroutineScope
): ChatRepository {

    val chatList = mutableListOf<ChatLog>()

    override suspend fun createChat(
        title: String,
        participantList: List<Participant>
    ): Result<String> = withContext(dataScope.coroutineContext) {
        val id = Uuid.random().toString()
        val newChat = ChatLog(id, title, TODO(""), messages = TODO())
        chatList += newChat
        Result.success(id)
    }

    override suspend fun getChat(id: String): Result<ChatLog> {
        return chatList.find { it.id == id }?.let {
            Result.success(it)
        } ?: Result.failure(IllegalArgumentException("No chat log with id: $id"))
    }
}
