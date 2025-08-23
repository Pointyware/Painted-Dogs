package org.pointyware.painteddogs.chat.data.fake

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import org.pointyware.painteddogs.chat.data.ChatRepository
import org.pointyware.painteddogs.chat.entities.Participant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class FakeChatRepository(
    private val dataScope: CoroutineScope
): ChatRepository {

    class ChatSession(val id: String, val title: String, val participants: List<Participant>)

    val chatList = mutableListOf<ChatSession>()

    override suspend fun createChat(
        title: String,
        participantList: List<Participant>
    ): Result<String> = withContext(dataScope.coroutineContext) {
        val id = Uuid.random().toString()
        val newChat = ChatSession(id, title, participantList)
        chatList += newChat
        Result.success(id)
    }
}
