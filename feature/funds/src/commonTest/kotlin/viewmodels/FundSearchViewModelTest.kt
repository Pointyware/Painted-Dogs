package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlinx.coroutines.test.runTest
import org.pointyware.kass.assertions.assertThat
import org.pointyware.kass.assertions.assumeThat
import org.pointyware.kass.assertions.objects.ObjectStatements.isEqualTo
import org.pointyware.kass.assertions.runScenarioWith
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

        runScenarioWith(listOf(viewModel)) {
            assumeThat(subject.state.value, isEqualTo(
                FundSearchUiState("", false, emptyList())
            ))

            subject.onSearchQueryChanged("puppy")
//            subject on SearchEvent.SearchQueryChanged("puppy")

            assertThat(subject.state.value, isEqualTo(
                FundSearchUiState("puppy", false, emptyList())
            ))
        }
    }
}
