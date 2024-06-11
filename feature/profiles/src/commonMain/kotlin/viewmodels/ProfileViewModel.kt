package org.pointyware.painteddogs.feature.profiles.viewmodels

import kotlinx.coroutines.flow.StateFlow

/**
 */
interface ProfileViewModel {
    val state: StateFlow<ProfileUiState>

    fun onEditProfile()
    fun onLogout()
}
