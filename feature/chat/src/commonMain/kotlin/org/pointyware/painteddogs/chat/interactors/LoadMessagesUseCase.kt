package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.data.ChatLog
import org.pointyware.painteddogs.chat.data.ChatRepository
import org.pointyware.painteddogs.chat.data.ContactRepository

class LoadMessagesUseCase(
    private val chatRepository: ChatRepository,
    private val contactRepository: ContactRepository
) {

    suspend fun invoke(chatId: String): Result<ChatLog> {
        return chatRepository.getChat(chatId).mapCatching { header ->
            val contacts = header.contactIds.map {
                contactRepository.getContactById(it).getOrThrow()
            }
            ChatLog(
                id = header.id,
                title = header.title,
                participants = contacts,
                messages = chatRepository.getChatMessages(chatId).getOrThrow(),
            )
        }
    }
}
