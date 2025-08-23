package org.pointyware.painteddogs.chat.entities

@Deprecated("Missing title", ReplaceWith("ChatPreview"))
data class ChatRecord(
    val id: String,
    val participants: List<Participant>,
    val excerpt: String
) {
}
