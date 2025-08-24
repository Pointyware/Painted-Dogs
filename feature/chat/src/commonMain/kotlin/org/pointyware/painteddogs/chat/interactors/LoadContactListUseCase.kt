package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.data.ContactRepository
import org.pointyware.painteddogs.chat.entities.Contact
import org.pointyware.painteddogs.chat.viewmodels.ChatHistoryDummyData

class LoadContactListUseCase(
    private val contactRepository: ContactRepository
) {

    suspend operator fun invoke(): Result<List<Contact>> {
        // TODO: load from contacts Repository
        return Result.success(listOf(
            ChatHistoryDummyData.userAbby,
            ChatHistoryDummyData.userAfton,
            ChatHistoryDummyData.userLink,
            ChatHistoryDummyData.userSarah,
        ))
    }
}
