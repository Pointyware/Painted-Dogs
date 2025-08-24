package org.pointyware.painteddogs.chat.interactors

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.pointyware.painteddogs.chat.data.AuthRepository
import org.pointyware.painteddogs.chat.data.ChatRepository
import org.pointyware.painteddogs.chat.entities.Contact
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class LoadMessagesUseCase(
    private val chatRepository: ChatRepository,
    private val authRepository: AuthRepository
) {

    suspend fun invoke(chatId: String): Result<List<ChatLogItem>> {
        return chatRepository.getChat(chatId).mapCatching { header ->
            val messages = chatRepository.getChatMessages(chatId).getOrThrow()

            val currentUser = authRepository.currentUser
            var lastDateTime: LocalDateTime? = null
            var lastAuthor: Contact? = null

            val elements = mutableListOf<ChatLogItem>()
            messages.forEach { message ->
                val newDateTime = message.timeSent.toLocalDateTime(TimeZone.currentSystemDefault())
                val newAuthor = message.sender

                if (newDateTime.year != lastDateTime?.year || newDateTime.month != lastDateTime.month || newDateTime.day != lastDateTime.day) {
                    elements.add(ChatLogItem.TimeDivider(message.timeSent))
                }
                if (newAuthor != lastAuthor && newAuthor != currentUser) {
                    elements.add(ChatLogItem.Author(message.sender))
                }
                elements.add(ChatLogItem.Message(
                    content = message.content,
                    timeStamp = message.timeSent,
                    isSender = message.sender == currentUser
                ))

                lastDateTime = newDateTime
                lastAuthor = newAuthor
            }
            elements.toList()
        }
    }
}
