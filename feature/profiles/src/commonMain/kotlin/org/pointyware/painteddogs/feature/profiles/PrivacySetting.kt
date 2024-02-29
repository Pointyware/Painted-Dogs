package org.pointyware.painteddogs.feature.profiles

/**
 *  Represents user privacy settings for transactions.
 */
interface PrivacySetting {
    val nameDetail: NameDetail
    val showEmail: Boolean
    val showPhone: Boolean
    sealed interface NameDetail {
        data class RealName(val showFirstName: Boolean, val showLastName: Boolean) : NameDetail
        data class Username(val showUsername: Boolean) : NameDetail
    }
}
