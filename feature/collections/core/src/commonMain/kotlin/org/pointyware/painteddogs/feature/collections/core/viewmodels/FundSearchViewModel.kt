package org.pointyware.painteddogs.feature.collections.core.viewmodels

import kotlinx.coroutines.flow.StateFlow

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
