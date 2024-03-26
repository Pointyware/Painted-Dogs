package org.pointyware.painteddogs.feature.collections.core.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.pointyware.painteddogs.feature.collections.core.UiTest
import kotlin.test.Test

/**
 *
 */
@OptIn(ExperimentalTestApi::class)
@UiTest
class FundCreationViewUnitTest {
    @Test
    fun `should display search input field`() = runComposeUiTest {
        // given - setup dependencies

        // when - the content is displayed
        setContent {
            FundCreationView(
                state = rememberFundCreationViewState(),
                onTitleChange = {},
                onDescriptionChange = {},
                onGoalChange = {},
                onStartDateSelectedChange = {},
                onEndDateSelectedChange = {},
                onSubmitDonation = {},
            )
        }

        // then - assert state
        onNodeWithText("Search ")
    }
}
