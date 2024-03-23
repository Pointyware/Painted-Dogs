package org.pointyware.painteddogs.feature.collections.core.viewmodels

import org.pointyware.painteddogs.assertions.assert
import org.pointyware.painteddogs.assertions.assume
import org.pointyware.painteddogs.assertions.runTestWith
import org.pointyware.painteddogs.core.viewmodels.SimpleTestViewModelImpl
import org.pointyware.painteddogs.feature.collections.core.test.TestFundRepository
import org.pointyware.painteddogs.feature.collections.core.viewmodels.test.SearchEvent
import org.pointyware.painteddogs.feature.collections.core.viewmodels.test.TestFundSearchViewModelImpl
import kotlin.test.Test


class FundSearchViewModelTest {
    @Test
    fun `searching for a collection updates the state`() {
        val repository = TestFundRepository()
        val viewModel = TestFundSearchViewModelImpl(SimpleTestViewModelImpl(CollectionSearchUiState.EMPTY))

        runTestWith(viewModel) {
            assume().that(subject.state.value).isEqualTo(
                CollectionSearchUiState("", false, emptyList())
            )

            subject on SearchEvent.SearchQueryChanged("puppy")

            assert().that(subject.state.value).isEqualTo(
                CollectionSearchUiState("puppy", false, emptyList())
            )
        }
    }
}
