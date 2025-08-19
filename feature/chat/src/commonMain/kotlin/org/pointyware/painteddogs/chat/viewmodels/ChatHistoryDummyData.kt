package org.pointyware.painteddogs.chat.viewmodels

import org.pointyware.painteddogs.chat.entities.ChatRecord
import org.pointyware.painteddogs.chat.entities.Participant

object ChatHistoryDummyData {
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
    val chatHistory = listOf(
        ChatRecord(
            id = "someId",
            participants = listOf(
                userSarah,
                userAbby
            ),
            excerpt = "When are we going to discuss global takeover?"
        ),
        ChatRecord(
            id = "antoehrId",
            participants = listOf(
                userLink,
                userAfton
            ),
            excerpt = "Coffee when?",
        ),
        ChatRecord(
            "direct-message",
            listOf(
                userSarah
            ),
            excerpt = "I finally updated my resume!",
        )
    )
}
