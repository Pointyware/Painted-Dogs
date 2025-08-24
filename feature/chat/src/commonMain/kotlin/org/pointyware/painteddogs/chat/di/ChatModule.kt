package org.pointyware.painteddogs.chat.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.pointyware.painteddogs.chat.data.ChatRepository
import org.pointyware.painteddogs.chat.data.ContactRepository
import org.pointyware.painteddogs.chat.data.fake.FakeChatRepository
import org.pointyware.painteddogs.chat.data.fake.FakeContactRepository
import org.pointyware.painteddogs.chat.interactors.AddParticipantUseCase
import org.pointyware.painteddogs.chat.interactors.CreateChatUseCase
import org.pointyware.painteddogs.chat.interactors.GetChatListUseCase
import org.pointyware.painteddogs.chat.interactors.GetChatPreview
import org.pointyware.painteddogs.chat.interactors.LoadContactListUseCase
import org.pointyware.painteddogs.core.data.di.dataQualifier

fun chatModule() = module {
    factoryOf(::AddParticipantUseCase)
    factoryOf(::CreateChatUseCase)
    factoryOf(::GetChatListUseCase)
    factoryOf(::GetChatPreview)
    factoryOf(::LoadContactListUseCase)

    factory<ChatRepository> {
        FakeChatRepository(
            get(qualifier = dataQualifier)
        )
    }
    factory<ContactRepository> {
        FakeContactRepository(
//            get(qualifier = dataQualifier)
        )
    }
}
