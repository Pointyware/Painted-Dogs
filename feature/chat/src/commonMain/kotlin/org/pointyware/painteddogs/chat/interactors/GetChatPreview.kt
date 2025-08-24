package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.data.ChatRepository
import org.pointyware.painteddogs.chat.data.ContactRepository

/**
 *
 */
class GetChatPreview(
    private val chatRepository: ChatRepository,
    private val contactRepository: ContactRepository
) {

    suspend fun invoke(id: String): Result<ChatPreview> {
        return chatRepository.getChat(id)
            .mapCatching { header ->
                val contacts = header.contactIds.map { contactId ->
                    contactRepository.getContactById(contactId).getOrThrow()
                }
                val excerpt = chatRepository.getExcerpt(header.id).getOrThrow()
                ChatPreview(
                    id = header.id,
                    title = header.title,
                    participants = contacts,
                    excerpt = excerpt
                )
            }
    }
}
