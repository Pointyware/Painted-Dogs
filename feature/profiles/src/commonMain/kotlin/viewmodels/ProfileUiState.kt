package org.pointyware.painteddogs.feature.profiles.viewmodels

import org.pointyware.painteddogs.core.entities.Uuid

/**
 *
 */
data class ProfileUiState(
    val id: Uuid,
    val username: String,
    val email: String,
    val avatarUrl: String,
    val isEmailVerified: Boolean,
)
