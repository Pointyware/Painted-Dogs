package org.pointyware.painteddogs.feature.profiles.viewmodels

/**
 *
 */
data class ProfileUiState(
    val id: String,
    val username: String,
    val email: String,
    val avatarUrl: String,
    val isEmailVerified: Boolean,
)
