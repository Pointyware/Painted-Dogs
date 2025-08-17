package org.pointyware.painteddogs.feature.profiles.ui

import org.pointyware.painteddogs.core.common.Mapper
import org.pointyware.painteddogs.feature.profiles.viewmodels.ProfileUiState
import kotlin.uuid.ExperimentalUuidApi

/**
 *
 */
object ProfileUiStateMapper: Mapper<ProfileUiState, UserProfileViewState> {
    @OptIn(ExperimentalUuidApi::class)
    override fun map(input: ProfileUiState): UserProfileViewState {
        return UserProfileViewState(
            userId = input.id,
            username = input.username,
            email = input.email,
            bio = "",
            profileImageUrl = input.avatarUrl,
        )
    }
}
