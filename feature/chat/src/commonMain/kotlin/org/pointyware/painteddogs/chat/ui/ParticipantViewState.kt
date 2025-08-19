package org.pointyware.painteddogs.chat.ui

/**
 * @param
 */
data class ParticipantViewState(
    val label: String,
    val image: ParticipantImage
)

sealed class ParticipantImage {
    class UnknownUserImage: ParticipantImage()
    object NoProfileImage: ParticipantImage()
    class ProfileImage: ParticipantImage()
}
