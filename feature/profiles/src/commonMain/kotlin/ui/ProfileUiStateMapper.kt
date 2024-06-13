package org.pointyware.painteddogs.feature.profiles.ui

import org.pointyware.painteddogs.feature.profiles.ProfileScreenState
import org.pointyware.painteddogs.feature.profiles.viewmodels.ProfileUiState

/**
 *
 */
object ProfileUiStateMapper {
    fun map(value: ProfileUiState): ProfileScreenState {
        return ProfileScreenState(
            value.id,
            value.username,
        )
    }
}
