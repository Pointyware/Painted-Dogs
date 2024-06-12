package org.pointyware.painteddogs.shared.home

import org.pointyware.painteddogs.core.common.Mapper
import org.pointyware.painteddogs.shared.screens.HomeScreenState

/**
 */
object HomeUiStateMapper: Mapper<HomeUiState, HomeScreenState> {
    override fun map(input: HomeUiState): HomeScreenState {
        return HomeScreenState(
            false,
            null,
            emptyList()
        )
    }
}
