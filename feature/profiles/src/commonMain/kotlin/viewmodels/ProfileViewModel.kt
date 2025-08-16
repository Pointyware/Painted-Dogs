package org.pointyware.painteddogs.feature.profiles.viewmodels

import kotlinx.coroutines.flow.StateFlow

/**
 */
interface ProfileViewModel {
    val state: StateFlow<ProfileUiState>

    fun onEditProfile()
    fun onLogout()
}

class ProfileViewModelImpl(

): ProfileViewModel {
    override val state: StateFlow<ProfileUiState>
        get() = TODO("Not yet implemented")

    override fun onEditProfile() {
        TODO("Not yet implemented")
    }

    override fun onLogout() {
        TODO("Not yet implemented")
    }
}
