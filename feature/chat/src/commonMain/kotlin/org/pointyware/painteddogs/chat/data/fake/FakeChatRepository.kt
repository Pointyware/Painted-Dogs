package org.pointyware.painteddogs.chat.data.fake

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import org.pointyware.painteddogs.chat.data.ChatRepository
import org.pointyware.painteddogs.chat.entities.Participant

class FakeChatRepository(
    private val dataScope: CoroutineScope
): ChatRepository {

    class ChatSession(val title: String, val participants: List<Participant>)

    val chatList = mutableListOf<ChatSession>()

    override suspend fun createChat(
        title: String,
        participantList: List<Participant>
    ): Result<Unit> = withContext(dataScope.coroutineContext) {
        Result.success(Unit)
    }
}
