package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlinx.coroutines.test.runTest
import org.pointyware.painteddogs.assertions.assert
import org.pointyware.painteddogs.assertions.assume
import org.pointyware.painteddogs.assertions.runTestWith
import org.pointyware.painteddogs.feature.funds.interactors.SearchCollectionsUseCaseImpl
import org.pointyware.painteddogs.feature.funds.test.TestFundRepositoryImpl
import kotlin.test.Test


class FundSearchViewModelTest {
    @Test
    fun `onQueryUpdated update ui state but does not trigger search`() = runTest {
        // given a view model and mock dependencies (use cases)
        val searchCollectionsUseCase = SearchCollectionsUseCaseImpl(TestFundRepositoryImpl())
        val viewModel = FundSearchViewModelImpl(searchCollectionsUseCase)

        // when the method under test is invoked with given arguments

        // then the expected use case is invoked with the expected arguments

        runTestWith(viewModel) {
            assume().that(subject.state.value).isEqualTo(
                FundSearchUiState("", false, emptyList())
            )

            subject.onSearchQueryChanged("puppy")
//            subject on SearchEvent.SearchQueryChanged("puppy")

            assert().that(subject.state.value).isEqualTo(
                FundSearchUiState("puppy", false, emptyList())
            )
        }
    }
}
