package org.pointyware.painteddogs.feature.profiles.viewmodels

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


/**
 *
 */
@OptIn(ExperimentalUuidApi::class)
data class ProfileUiState(
    val id: Uuid,
    val username: String,
    val email: String,
    val avatarUrl: String,
    val isEmailVerified: Boolean,
)
