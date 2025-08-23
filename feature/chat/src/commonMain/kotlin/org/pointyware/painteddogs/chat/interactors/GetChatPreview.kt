package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.data.ChatRepository

/**
 *
 */
class GetChatPreview(
    private val chatRepository: ChatRepository
) {

    suspend fun invoke(id: String): Result<ChatPreview> {
        TODO("")
    }
}
