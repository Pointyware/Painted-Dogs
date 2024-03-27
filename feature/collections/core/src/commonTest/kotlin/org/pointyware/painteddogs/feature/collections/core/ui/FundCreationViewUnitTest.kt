package org.pointyware.painteddogs.feature.collections.core.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.pointyware.painteddogs.feature.collections.core.UiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
@UiTest
class FundCreationViewUnitTest {
    @Test
    fun `should display search input field`() = runComposeUiTest {
        // given - setup dependencies
        val donationState = FundCreationViewState(
            title = "Some title",
            description = "Some description",
            targetAmount = "$500",
            startDate = "January 1",
            endDate = "December 2",
        )

        // when - the content is displayed
        setContent {
            FundCreationView(
                state = donationState,
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
