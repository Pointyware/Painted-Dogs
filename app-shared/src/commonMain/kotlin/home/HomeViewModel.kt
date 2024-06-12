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

class HomeViewModelImpl: HomeViewModel {
    override val state: StateFlow<HomeUiState>
        get() = TODO("Not yet implemented")
}
