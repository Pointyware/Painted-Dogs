package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.data.ChatRepository

/**
 *
 */
class GetChatListUseCase(
    private val chatRepository: ChatRepository,
    private val getChatPreview: GetChatPreview
) {

    suspend fun invoke(): Result<List<ChatPreview>> {
        return chatRepository.getChatList().mapCatching { list ->
            list.map { header ->
                getChatPreview.invoke(header.id).getOrThrow()
            }
        }
    }
}
