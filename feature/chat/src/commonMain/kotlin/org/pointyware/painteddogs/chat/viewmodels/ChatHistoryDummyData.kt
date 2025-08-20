package org.pointyware.painteddogs.chat.viewmodels

import org.pointyware.painteddogs.chat.entities.ChatRecord
import org.pointyware.painteddogs.chat.entities.Participant

object ChatHistoryDummyData {
    val userTaush = Participant(
        name = "Taush",
        userId = "uuid-for-me"
    )
    val userSarah = Participant(
        name = "Sarah",
        userId = "uuid-for-sarah"
    )
    val userAbby = Participant(
        name = "Abigail",
        userId = "uuid-for-abigail"
    )
    val userLink = Participant(
        name = "Link",
        userId = "uuid-for-link"
    )
    val userAfton = Participant(
        name = "Afton",
        userId = "uuid-for-afton"
    )

    val chatSarahAbby = ChatRecord(
        id = "someId",
        participants = listOf(
            userSarah,
            userAbby
        ),
        excerpt = "When are we going to discuss global takeover?"
    )
    val chatLinkAfton = ChatRecord(
        id = "antoehrId",
        participants = listOf(
            userLink,
            userAfton
        ),
        excerpt = "Coffee when?",
    )
    val chatSarah = ChatRecord(
        "direct-message",
        listOf(
            userSarah
        ),
        excerpt = "I finally updated my resume!",
    )
    val chatHistory = listOf(
        chatSarahAbby,
        chatLinkAfton,
        chatSarah
    )
}
