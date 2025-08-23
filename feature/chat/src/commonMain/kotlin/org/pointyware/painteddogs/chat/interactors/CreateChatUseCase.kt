package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.data.ChatRepository
import org.pointyware.painteddogs.chat.entities.Participant

class CreateChatUseCase(
    private val chatRepository: ChatRepository
) {
    suspend fun invoke(title: String, participantList: List<Participant>): Result<String> {
        return chatRepository.createChat(title, participantList)
    }
}
