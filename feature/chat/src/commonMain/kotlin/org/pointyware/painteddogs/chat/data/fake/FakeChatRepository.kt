package org.pointyware.painteddogs.chat.data.fake

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import org.pointyware.painteddogs.chat.data.ChatHeader
import org.pointyware.painteddogs.chat.data.ChatLog
import org.pointyware.painteddogs.chat.data.ChatMessage
import org.pointyware.painteddogs.chat.data.ChatRepository
import org.pointyware.painteddogs.chat.entities.Contact
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryDummyData
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryDummyData.userAbby
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryDummyData.userAfton
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryDummyData.userLink
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryDummyData.userSarah
import kotlin.time.Clock
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)
class FakeChatRepository(
    private val dataScope: CoroutineScope
): ChatRepository {

    val chatList = mutableListOf<ChatLog>()

    private val start = Clock.System.now()
    init {
        chatList.add(
            ChatLog(
                id = "someId",
                title = "Democracy",
                participants = listOf(
                    userSarah,
                    userAbby
                ),
                messages = listOf(
                    ChatMessage(
                        id = "0",
                        ChatHistoryDummyData.userTaush,
                        "When is the next PCD meeting?",
                        start
                    ),
                    ChatMessage(
                        id = "1",
                        ChatHistoryDummyData.userSarah,
                        "The system!",
                        start - 1.hours
                    ),
                    ChatMessage(
                        id = "2",
                        ChatHistoryDummyData.userAbby,
                        "I concur.",
                        start - 1.hours - 20.minutes
                    ),
                    ChatMessage(
                        id = "3",
                        ChatHistoryDummyData.userSarah,
                        "Democracy!",
                        start - 2.hours
                    ),
                )
            )
        )
        chatList.add(
            ChatLog(
                id = "antoehrId",
                title = "Coffee",
                participants = listOf(
                    userLink,
                    userAfton
                ),
                messages = listOf(
                    ChatMessage(
                        id = "4",
                        ChatHistoryDummyData.userAfton,
                        "But the meeting can rice, perhaps?",
                        start - 2.minutes
                    ),
                    ChatMessage(
                        id = "5",
                        ChatHistoryDummyData.userLink,
                        "My flight got delayed. Rice will not be meeting today.",
                        start - 1.hours
                    ),
                    ChatMessage(
                        id = "6",
                        ChatHistoryDummyData.userAfton,
                        "Lawyers can get bent.",
                        start - 1.5.hours
                    ),
                    ChatMessage(
                        id = "7",
                        ChatHistoryDummyData.userLink,
                        "Rice and meetings, rice and meetings?",
                        start - 2.hours
                    ),
                )
            )
        )
        chatList.add(
            ChatLog(
                id = "direct-message",
                title = "Resume",
                listOf(
                    userSarah
                ),
                messages = listOf(
                    ChatMessage(
                        id = "8",
                        ChatHistoryDummyData.userTaush,
                        "I finally finished my resume!",
                        start - 1.minutes
                    ),
                    ChatMessage(
                        id = "9",
                        ChatHistoryDummyData.userSarah,
                        "Hellooooo?",
                        start - 48.minutes
                    ),
                    ChatMessage(
                        id = "10",
                        ChatHistoryDummyData.userSarah,
                        "Is your resume ready?",
                        start - 5.hours
                    )
                )
            )
        )
    }

    override suspend fun createChat(
        title: String,
        participantList: List<Contact>
    ): Result<String> = withContext(dataScope.coroutineContext) {
        val id = Uuid.random().toString()
        val newChat = ChatLog(id, title, participantList, messages = emptyList())
        chatList += newChat
        Result.success(id)
    }

    override suspend fun getChat(id: String): Result<ChatHeader> {
        return chatList.find { it.id == id }?.let {
            Result.success(ChatHeader(it.id, it.title, it.participants.map { it.id} ))
        } ?: Result.failure(IllegalArgumentException("No chat log with id: $id"))
    }

    override suspend fun getChatList(): Result<List<ChatHeader>> {
        val list = chatList.map { log ->
            ChatHeader(log.id, log.title, log.participants.map { it.id} )
        }
        return Result.success(list)
    }

    override suspend fun getExcerpt(id: String): Result<String> {
        return chatList.find { it.id == id }?.let { log ->
            val excerpt = log.messages.lastOrNull()?.let { message ->
                "${message.sender.username}: ${message.content}"
            } ?: ""
            Result.success(excerpt)
        } ?: Result.failure(IllegalArgumentException("No chat log with id: $id"))
    }

    override suspend fun getChatMessages(chatId: String): Result<List<ChatMessage>> = runCatching {
        chatList.find { it.id == chatId }?.messages
            ?: throw IllegalArgumentException("No chat log with id: $chatId")
    }
}
