package org.pointyware.painteddogs.chat.viewmodels

import org.pointyware.painteddogs.chat.entities.Participant
import org.pointyware.painteddogs.chat.interactors.ChatPreview

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

    val chatSarahAbby = ChatPreview(
        id = "someId",
        title = "Democracy",
        participants = listOf(
            userSarah,
            userAbby
        ),
        excerpt = "When is the next PCD meeting?"
    )
    val chatLinkAfton = ChatPreview(
        id = "antoehrId",
        title = "Coffee",
        participants = listOf(
            userLink,
            userAfton
        ),
        excerpt = "Coffee when?",
    )
    val chatSarah = ChatPreview(
        "direct-message",
        title = "Resume",
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
