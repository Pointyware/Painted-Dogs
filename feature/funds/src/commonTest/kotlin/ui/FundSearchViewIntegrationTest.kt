package org.pointyware.painteddogs.feature.collections.core.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.pointyware.painteddogs.feature.collections.core.UxTest
import ui.FundSearchView
import ui.FundSearchViewState
import kotlin.test.Test

/**
 *
 */
@OptIn(ExperimentalTestApi::class)
@UxTest
class FundSearchViewIntegrationTest {
    @Test
    fun `should display search input field`() = runComposeUiTest {
        // given - setup dependencies

        // when - the content is displayed
        setContent {
            FundSearchView(
                state = FundSearchViewState(),
                onSearchQueryChanged = {},
                onSearchQuerySubmitted = {},
                onFundSelected = {},
            )
        }

        // then - assert state
        onNodeWithText("Search ")
    }
}
