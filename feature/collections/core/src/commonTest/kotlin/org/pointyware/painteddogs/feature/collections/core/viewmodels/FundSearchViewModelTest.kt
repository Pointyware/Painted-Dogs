package org.pointyware.painteddogs.feature.collections.core.viewmodels

import kotlinx.coroutines.test.runTest
import org.pointyware.painteddogs.assertions.assert
import org.pointyware.painteddogs.assertions.assume
import org.pointyware.painteddogs.assertions.runTestWith
import org.pointyware.painteddogs.feature.collections.core.UiTest
import org.pointyware.painteddogs.feature.collections.core.interactors.SearchCollectionsUseCaseImpl
import org.pointyware.painteddogs.feature.collections.core.test.TestFundRepository
import kotlin.test.Test


class FundSearchViewModelTest {
    @Test
    fun `onQueryUpdated update ui state but does not trigger search`() = runTest {
        // given a view model and mock dependencies (use cases)
        val searchCollectionsUseCase = SearchCollectionsUseCaseImpl(TestFundRepository())
        val viewModel = FundSearchViewModelImpl(searchCollectionsUseCase)

        // when the method under test is invoked with given arguments

        // then the expected use case is invoked with the expected arguments

        runTestWith(viewModel) {
            assume().that(subject.state.value).isEqualTo(
                CollectionSearchUiState("", false, emptyList())
            )

            subject.onSearchQueryChanged("puppy")
//            subject on SearchEvent.SearchQueryChanged("puppy")

            assert().that(subject.state.value).isEqualTo(
                CollectionSearchUiState("puppy", false, emptyList())
            )
        }
    }
}

@UiTest
class FundSearchViewUiTest {
    @Test
    fun `searching for a collection updates the state`() {
        // given ui and a view model
        // when the expected UI elements is pressed
        // then the expected view model event is fired with the expected arguments
    }
}
