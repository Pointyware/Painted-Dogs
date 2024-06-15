package org.pointyware.painteddogs.feature.funds.viewmodels.test

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.pointyware.painteddogs.core.viewmodels.SimpleTestViewModel
import org.pointyware.painteddogs.feature.funds.viewmodels.FundSearchUiState
import org.pointyware.painteddogs.feature.funds.viewmodels.FundSearchViewModel


sealed interface SearchEvent {
    data class SearchQueryChanged(val query: String): SearchEvent
    data class SearchQuerySubmitted(val query: String): SearchEvent
}

class TestFundSearchViewModelImpl(
    private val testSimpleTestViewModel: SimpleTestViewModel<FundSearchUiState, SearchEvent>
): FundSearchViewModel, SimpleTestViewModel<FundSearchUiState, SearchEvent> by testSimpleTestViewModel {

    override val state: StateFlow<FundSearchUiState> = MutableStateFlow(FundSearchUiState.EMPTY)

    override fun onSearchQueryChanged(query: String) {
        testSimpleTestViewModel on SearchEvent.SearchQueryChanged(query)
    }

    override fun onSubmitQuery(query: String) {
        testSimpleTestViewModel on SearchEvent.SearchQuerySubmitted(query)
    }
}
