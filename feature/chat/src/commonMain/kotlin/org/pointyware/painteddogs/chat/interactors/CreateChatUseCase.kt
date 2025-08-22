package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.data.ChatRepository
import org.pointyware.painteddogs.chat.entities.Participant

class CreateChatUseCase(
    private val chatRepository: ChatRepository
) {
    suspend fun invoke(title: String, participantList: List<Participant>): Result<Unit> {
        return chatRepository.createChat(title, participantList)
    }
}
