package org.pointyware.painteddogs.shared.home

import kotlinx.coroutines.flow.StateFlow

/**
 *
 */
interface HomeViewModel {
    val state: StateFlow<HomeUiState>
}

class HomeUiState {

}
