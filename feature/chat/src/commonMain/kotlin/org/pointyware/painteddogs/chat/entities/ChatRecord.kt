package org.pointyware.painteddogs.chat.entities

data class ChatRecord(
    val id: String,
    val participants: List<Participant>,
    val excerpt: String
) {
}
