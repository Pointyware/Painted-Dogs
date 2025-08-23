package org.pointyware.painteddogs.chat.viewmodels

import org.pointyware.painteddogs.chat.entities.Contact
import org.pointyware.painteddogs.chat.interactors.ChatPreview

object ChatHistoryDummyData {
    val userTaush = Contact(
        username = "Taush",
        id = "uuid-for-me"
    )
    val userSarah = Contact(
        username = "Sarah",
        id = "uuid-for-sarah"
    )
    val userAbby = Contact(
        username = "Abigail",
        id = "uuid-for-abigail"
    )
    val userLink = Contact(
        username = "Link",
        id = "uuid-for-link"
    )
    val userAfton = Contact(
        username = "Afton",
        id = "uuid-for-afton"
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
