package org.pointyware.painteddogs.feature.collections.core.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.pointyware.painteddogs.feature.collections.core.interactors.SearchCollectionsUseCase

/**
 * ViewModel for searching donations/collections.
 */
interface FundSearchViewModel {
    val state: StateFlow<CollectionSearchUiState>
    /**
     * Called when the search query changes.
     */
    fun onSearchQueryChanged(query: String)
    /**
     * Called when the search query is submitted.
     */
    fun onSubmitQuery(query: String)
}

/**
 *
 */
class FundSearchViewModelImpl(
    private val searchCollectionsUseCase: SearchCollectionsUseCase
): FundSearchViewModel {
    private val _state = MutableStateFlow(CollectionSearchUiState.EMPTY)
    override val state: StateFlow<CollectionSearchUiState>
        get() = _state

    override fun onSearchQueryChanged(query: String) {
        _state.value = CollectionSearchUiState(query, false, emptyList())
    }

    override fun onSubmitQuery(query: String) {

    }
}
