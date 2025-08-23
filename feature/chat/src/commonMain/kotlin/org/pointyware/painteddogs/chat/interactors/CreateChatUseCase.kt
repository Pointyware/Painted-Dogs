package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.data.ChatRepository
import org.pointyware.painteddogs.chat.entities.Contact

class CreateChatUseCase(
    private val chatRepository: ChatRepository
) {
    suspend fun invoke(title: String, participantList: List<Contact>): Result<String> {
        return chatRepository.createChat(title, participantList)
    }
}
