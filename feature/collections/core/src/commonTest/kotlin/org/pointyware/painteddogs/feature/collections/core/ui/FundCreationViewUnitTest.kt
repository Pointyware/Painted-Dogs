package org.pointyware.painteddogs.feature.collections.core.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import io.mockative.Fun0
import io.mockative.Fun1
import io.mockative.classOf
import io.mockative.mock
import io.mockative.verify
import org.pointyware.painteddogs.feature.collections.core.UiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
@UiTest
class FundCreationViewUnitTest {
    @Test
    fun `should display search input field`() = runComposeUiTest {
        // Test Setup
        val onEvent = mock(classOf<Fun0<Unit>>())
        val onStringEvent = mock(classOf<Fun1<String, Unit>>()) // Mock callback stub
        val onBooleanEvent = mock(classOf<Fun1<Boolean, Unit>>())
        val onDoubleEvent = mock(classOf<Fun1<Double, Unit>>())

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
                onTitleChange = onStringEvent::invoke,
                onDescriptionChange = onStringEvent::invoke,
                onGoalChange = onDoubleEvent::invoke,
                onStartDateSelectedChange = onBooleanEvent::invoke,
                onEndDateSelectedChange = onBooleanEvent::invoke,
                onSubmitDonation = onEvent::invoke,
            )
        }

        // then - assert state
        onNodeWithText("Search ", substring = true)
        // Input Simulation (Using semantic properties)
        onNode(hasText("Description")).performTextInput("Test Description")
        onNodeWithText("Title").performTextInput("Test Title")
        onNodeWithText("Goal").performTextInput("1000.00")

        // Side-Effect Verification
        verify { onStringEvent.invoke("Some title"); "" }

    }
}
