package org.pointyware.painteddogs.feature.collections.core.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
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

    @AfterTest
    fun tearDown() {

    }

    @Test
    fun `empty state`() = runComposeUiTest {
        // given an empty state
        val emptyState = FundCreationViewState(
            title = "",
            description = "",
            targetAmount = "",
            startDate = "",
            endDate = "",
        )

        // when - the content is displayed
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

        // then - assert state
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
    fun `filled state`() = runComposeUiTest {
        // given some initial valid state
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
    fun `When Submit Button is tapped Then invoke callback Create Collection`() = runComposeUiTest {
        // given some initial empty state

        // when - the content is displayed
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
        verify { onStringEvent.invoke("Test Title") }.wasInvoked(once)
        verify { onStringEvent.invoke("Test Description") }.wasInvoked(once)
        verify { onDoubleEvent.invoke(1000.0) }.wasInvoked(once)
        verify { onEvent.invoke() }.wasInvoked(once)
    }
}
