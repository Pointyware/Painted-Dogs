package org.pointyware.painteddogs.chat.entities

/**
 * An individual
 */
data class Participant(
    val name: String?,
    val userId: String
) {

    val label: String get() = name ?: userId.takeLast(8)

}
