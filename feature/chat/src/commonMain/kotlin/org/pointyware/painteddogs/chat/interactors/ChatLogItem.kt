package org.pointyware.painteddogs.chat.interactors

import org.pointyware.painteddogs.chat.entities.Contact
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 *
 */
sealed interface ChatLogItem {

    /**
     * A time divider is inserted at the beginning of each day.
     */
    @OptIn(ExperimentalTime::class)
    class TimeDivider(val day: Instant): ChatLogItem

    /**
     * An author indicator is inserted before the first message of a given author.
     */
    class Author(val contact: Contact): ChatLogItem

    /**
     * A message displays the content and specific time of an entry in the chat log.
     */
    @OptIn(ExperimentalTime::class)
    class Message(
        val content: String,
        val timeStamp: Instant,
        val isSender: Boolean
    ): ChatLogItem
}
