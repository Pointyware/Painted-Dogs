package org.pointyware.painteddogs.feature.collections.core.viewmodels.test

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.pointyware.painteddogs.core.viewmodels.SimpleTestViewModel
import org.pointyware.painteddogs.feature.collections.core.viewmodels.CollectionSearchUiState
import org.pointyware.painteddogs.feature.collections.core.viewmodels.FundSearchViewModel


sealed interface SearchEvent {
    data class SearchQueryChanged(val query: String): SearchEvent
    data class SearchQuerySubmitted(val query: String): SearchEvent
}

class TestFundSearchViewModelImpl(
    private val testSimpleTestViewModel: SimpleTestViewModel<CollectionSearchUiState, SearchEvent>
): FundSearchViewModel, SimpleTestViewModel<CollectionSearchUiState, SearchEvent> by testSimpleTestViewModel {

    override val state: StateFlow<CollectionSearchUiState> = MutableStateFlow(CollectionSearchUiState.EMPTY)

    override fun onSearchQueryChanged(query: String) {
        testSimpleTestViewModel on SearchEvent.SearchQueryChanged(query)
    }

    override fun onSubmitQuery(query: String) {
        testSimpleTestViewModel on SearchEvent.SearchQuerySubmitted(query)
    }
}
