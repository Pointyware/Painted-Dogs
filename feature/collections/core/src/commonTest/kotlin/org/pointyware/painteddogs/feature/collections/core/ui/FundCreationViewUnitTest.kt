package org.pointyware.painteddogs.feature.collections.core.ui

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.mockative.Fun0
import io.mockative.Fun1
import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.doesNothing
import io.mockative.every
import io.mockative.mock
import io.mockative.once
import io.mockative.verify
import org.pointyware.painteddogs.feature.collections.core.UiTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
@UiTest
class FundCreationViewUnitTest {
    @Mock
    val onEvent = mock(classOf<Fun0<Unit>>())
    @Mock
    val onStringEvent = mock(classOf<Fun1<String, Unit>>())
    @Mock
    val onBooleanEvent = mock(classOf<Fun1<Boolean, Unit>>())
    @Mock
    val onDoubleEvent = mock(classOf<Fun1<Double, Unit>>())

    @BeforeTest
    fun setUp() {
        every { onStringEvent.invoke(any()) }.doesNothing()
    }

    fun SemanticsNodeInteraction.assertSizeIsAtLeast(width: Dp, height: Dp): SemanticsNodeInteraction {
        return this.assertHeightIsAtLeast(height)
            .assertWidthIsAtLeast(width)
    }

    fun SemanticsNodeInteraction.assertSizeIsMinimumTouchSize(): SemanticsNodeInteraction {
        return this.assertSizeIsAtLeast(48.dp, 48.dp)
    }

    fun SemanticsNodeInteraction.assertNorms(): SemanticsNodeInteraction {
        return this.assertIsEnabled()
            .assertSizeIsMinimumTouchSize()
    }

    private fun ComposeUiTest.invariants() {
        // title
        onNodeWithText("Create Collection")
            .assertNorms()
        // collection details
        onNodeWithText("Title")
            .assertNorms()
        onNodeWithText("Description")
            .assertNorms()
        onNodeWithText("Goal")
            .assertNorms()
        // media

        // dates
        onNodeWithText("Start Date")
            .assertNorms()
        onNodeWithText("End Date")
            .assertNorms()
        // submission
        onNodeWithText("Submit")
            .assertNorms()
    }

    @AfterTest
    fun tearDown() {

    }

    @Test
    fun `State - Empty`() = runComposeUiTest {
        // Given an empty state
        val emptyState = FundCreationViewState(
            title = "",
            description = "",
            targetAmount = "",
            startDate = "",
            endDate = "",
        )

        // When the content is displayed
        setContent {
            FundCreationView(
                state = emptyState,
                onTitleChange = onStringEvent::invoke,
                onDescriptionChange = onStringEvent::invoke,
                onGoalChange = onDoubleEvent::invoke,
                onStartDateSelectedChange = onBooleanEvent::invoke,
                onEndDateSelectedChange = onBooleanEvent::invoke,
                onSubmitDonation = onEvent::invoke,
            )
        }

        // Then UI is empty
        invariants()
        // title
        onNodeWithText("Create Collection")
            .assertExists()
        // collection details
        onNodeWithText("Title")
            .assertExists()
        onNodeWithText("Description")
            .assertExists()
        onNodeWithText("Goal")
            .assertExists()
        // media

        // dates
        onNodeWithText("Start Date")
            .assertExists()
        onNodeWithText("End Date")
            .assertExists()
        // submission
        onNodeWithText("Submit")
            .assertExists()
    }

    @Test
    fun `State - Filled`() = runComposeUiTest {
        // Given some initial valid state
        val donationState = FundCreationViewState(
            title = "Some title",
            description = "Some description",
            targetAmount = "$500",
            startDate = "January 1",
            endDate = "December 2",
        )

        // When the content is displayed
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

        // Then UI is filled
        invariants()
        // title
        onNodeWithText("Create Collection")
            .assertExists()
        // collection details
        onNodeWithText("Title")
            .assertExists()
        onNodeWithText("Description")
            .assertExists()
        onNodeWithText("Goal")
            .assertExists()
        // media

        // dates
        onNodeWithText("Start Date")
            .assertExists()
        onNodeWithText("End Date")
            .assertExists()
        // submission
        onNodeWithText("Submit")
            .assertExists()
    }

    @Test
    fun `State - `() = runComposeUiTest{
        // Given some state

        // When the content is displayed

        // Then assert state
        invariants()
    }

    @Test
    fun `Event - When Submit Button is tapped Then invoke callback Create Collection`() = runComposeUiTest {
        // Given some initial empty state

        // When - the content is displayed
        setContent {
            FundCreationView(
                state = rememberFundCreationViewState(),
                onTitleChange = onStringEvent::invoke,
                onDescriptionChange = onStringEvent::invoke,
                onGoalChange = onDoubleEvent::invoke,
                onStartDateSelectedChange = onBooleanEvent::invoke,
                onEndDateSelectedChange = onBooleanEvent::invoke,
                onSubmitDonation = onEvent::invoke,
            )
        }
        onNodeWithText("Title").performTextInput("Test Title")
        onNodeWithText("Description").performTextInput("Test Description")
        onNodeWithText("Goal").performTextInput("1000.00")
        onNodeWithText("Submit").performClick()

        // Then methods are invoked with same arguments
        invariants()
        verify { onStringEvent.invoke("Test Title") }.wasInvoked(once)
        verify { onStringEvent.invoke("Test Description") }.wasInvoked(once)
        verify { onDoubleEvent.invoke(1000.0) }.wasInvoked(once)
        verify { onEvent.invoke() }.wasInvoked(once)
    }

    @Test
    fun `Event - `() = runComposeUiTest{
        // Given some state

        // When the content is displayed

        // Then assert state
        invariants()
    }
}
