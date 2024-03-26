package org.pointyware.painteddogs.feature.collections.core.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.pointyware.painteddogs.feature.collections.core.EndToEndTest
import org.pointyware.painteddogs.feature.collections.core.UiTest
import kotlin.test.Test

/**
 *
 */
@OptIn(ExperimentalTestApi::class)
@UiTest
@EndToEndTest
class FundSearchViewE2ETest {
    @Test
    fun `success - happy path`() = runComposeUiTest {
        // given - setup production dependencies/initial state
        setContent {
            FundSearchView(
                state = FundSearchViewState(),
                onSearchQueryChanged = {},
                onSearchQuerySubmitted = {},
                onFundSelected = {},
            )
        }

        // when - some action
        // then - expected result
        // when - etc. for whole user journey
    }

    @Test
    fun `edge - long title`() = runComposeUiTest {
        // given - setup production dependencies/initial state
        setContent {
            FundSearchView(
                state = FundSearchViewState(),
                onSearchQueryChanged = {},
                onSearchQuerySubmitted = {},
                onFundSelected = {},
            )
        }

        // when - some action
        // then - expected result
        // when - etc. for whole user journey
    }

    @Test
    fun `edge - long description`() = runComposeUiTest {
        // given - setup production dependencies/initial state
        setContent {
            FundSearchView(
                state = FundSearchViewState(),
                onSearchQueryChanged = {},
                onSearchQuerySubmitted = {},
                onFundSelected = {},
            )
        }

        // when - some action
        // then - expected result
        // when - etc. for whole user journey
    }

    @Test
    fun `corner - long title and description`() = runComposeUiTest {
        // given - setup production dependencies/initial state
        setContent {
            FundSearchView(
                state = FundSearchViewState(),
                onSearchQueryChanged = {},
                onSearchQuerySubmitted = {},
                onFundSelected = {},
            )
        }

        // when - some action
        // then - expected result
        // when - etc. for whole user journey
    }

    @Test
    fun `failure - goal amount too small`() = runComposeUiTest {
        // given - setup production dependencies/initial state
        setContent {
            FundSearchView(
                state = FundSearchViewState(),
                onSearchQueryChanged = {},
                onSearchQuerySubmitted = {},
                onFundSelected = {},
            )
        }

        // when - some action
        // then - expected result
        // when - etc. for whole user journey
    }

    @Test
    fun `failure - empty title`() = runComposeUiTest {
        // given - setup production dependencies/initial state
        setContent {
            FundSearchView(
                state = FundSearchViewState(),
                onSearchQueryChanged = {},
                onSearchQuerySubmitted = {},
                onFundSelected = {},
            )
        }

        // when - some action
        // then - expected result
        // when - etc. for whole user journey
    }
}
