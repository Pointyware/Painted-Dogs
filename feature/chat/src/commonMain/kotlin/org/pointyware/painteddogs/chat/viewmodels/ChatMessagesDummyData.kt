package org.pointyware.painteddogs.chat.viewmodels

import org.pointyware.painteddogs.chat.entities.Message
import kotlin.time.Clock
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
object ChatMessagesDummyData {
    val start = Clock.System.now()
    val chatMessageMap = mapOf(
        ChatHistoryDummyData.chatSarahAbby.id to listOf(
            Message(
                ChatHistoryDummyData.userTaush,
                "When are we going to discuss global domination?",
                start
            ),
            Message(
                ChatHistoryDummyData.userSarah,
                "The system!",
                start - 1.hours
            ),
            Message(
                ChatHistoryDummyData.userAbby,
                "I concur.",
                start - 1.hours - 20.minutes
            ),
            Message(
                ChatHistoryDummyData.userSarah,
                "Democracy!",
                start - 2.hours
            ),
        ),

        ChatHistoryDummyData.chatLinkAfton.id to listOf(
            Message(
                ChatHistoryDummyData.userAfton,
                "But the meeting can rice, perhaps?",
                start - 2.minutes
            ),
            Message(
                ChatHistoryDummyData.userLink,
                "My flight got delayed. Rice will not be meeting today.",
                start - 1.hours
            ),
            Message(
                ChatHistoryDummyData.userAfton,
                "Lawyers can get bent.",
                start - 1.5.hours
            ),
            Message(
                ChatHistoryDummyData.userLink,
                "Rice and meetings, rice and meetings?",
                start - 2.hours
            ),
        ),

        ChatHistoryDummyData.chatSarah.id to listOf(
            Message(
                ChatHistoryDummyData.userTaush,
                "I finally finished my resume!",
                start - 1.minutes
            ),
            Message(
                ChatHistoryDummyData.userSarah,
                "Hellooooo?",
                start - 48.minutes
            ),
            Message(
                ChatHistoryDummyData.userSarah,
                "Is your resume ready?",
                start - 5.hours
            ),
        )
    )
}
