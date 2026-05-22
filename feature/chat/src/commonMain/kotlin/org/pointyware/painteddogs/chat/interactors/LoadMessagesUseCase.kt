package org.pointyware.painteddogs.chat.interactors

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.pointyware.painteddogs.chat.data.AuthRepository
import org.pointyware.painteddogs.chat.data.ChatRepository
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class LoadMessagesUseCase(
    private val chatRepository: ChatRepository,
    private val authRepository: AuthRepository
) {

    suspend fun invoke(chatId: String): Result<List<ChatLogItem>> {
        return chatRepository.getChat(chatId).mapCatching { header ->
            val messages = chatRepository.getChatMessages(chatId).getOrThrow()

            if (messages.isEmpty()) return@mapCatching emptyList()
            val elements = mutableListOf<ChatLogItem>()

            val currentUser = authRepository.currentUser

            val systemTimeZone = TimeZone.currentSystemDefault()
            var lastMessage = messages.first()
            var lastDateTime: LocalDateTime? = lastMessage.timeSent.toLocalDateTime(systemTimeZone)

            messages.forEach { message ->
                val newDateTime = message.timeSent.toLocalDateTime(systemTimeZone)
                val newAuthor = message.sender

                if (newDateTime.year != lastDateTime?.year || newDateTime.month != lastDateTime.month || newDateTime.day != lastDateTime.day) {
                    elements.add(ChatLogItem.TimeDivider(lastMessage.timeSent))
                }
                if (newAuthor != lastMessage.sender && newAuthor != currentUser) {
                    elements.add(ChatLogItem.Author(lastMessage.sender))
                }
                elements.add(ChatLogItem.Message(
                    content = message.content,
                    timeStamp = message.timeSent,
                    isSender = message.sender == currentUser
                ))

                lastDateTime = newDateTime
                lastMessage = message
            }
            elements.toList()
        }
    }
}
