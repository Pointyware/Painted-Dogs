package org.pointyware.painteddogs.feature.funds.ui

import androidx.compose.ui.test.ExperimentalTestApi
import org.pointyware.painteddogs.core.ui.components.DateSelectorRow
import org.pointyware.painteddogs.core.ui.components.DateSelectorRowState
import org.pointyware.painteddogs.feature.funds.UiTest

@OptIn(ExperimentalTestApi::class)
@UiTest
class DateSelectorRowUnitTest {
/*
    @Mock
    val onDateChanged = mock(classOf<Fun1<Instant?, Unit>>())

    @BeforeTest
    fun setUp() {
        every { onDateChanged.invoke(any()) }.doesNothing()
    }

    @Test
    fun `When no date is selected Then display Select Date button`() = runComposeUiTest {
        // Given an empty state
        val emptyState = DateSelectorRowState(date = null)

        // When the content is displayed
        setContent {
            DateSelectorRow(
                state = emptyState,
                onDateChanged = onDateChanged::invoke,
            )
        }

        // Then UI displays Select Date button
        onNodeWithText("Select Date")
            .assertExists()
    }

    @Test
    fun `When Select Date is clicked Then date picker dialog is shown`() = runComposeUiTest {
        // Given an empty state
        val emptyState = DateSelectorRowState(date = null)

        // When the content is displayed and Select Date button is clicked
        setContent {
            DateSelectorRow(
                state = emptyState,
                onDateChanged = onDateChanged::invoke,
            )
        }
        onNodeWithText("Select Date").performClick()

        // Then date picker dialog is shown
        onNodeWithText("Select date")
            .assertExists()
        onNodeWithText("Cancel")
            .assertExists()
        onNodeWithText("OK")
            .assertExists()
    }

    @Test
    fun `When Cancel in date picker dialog is clicked Then callback is not invoked`() = runComposeUiTest {
        // Given an empty state
        val emptyState = DateSelectorRowState(date = null)

        // When the content is displayed and Select Date button is clicked
        setContent {
            DateSelectorRow(
                state = emptyState,
                onDateChanged = onDateChanged::invoke,
            )
        }
        onNodeWithText("Select Date").performClick()

        // When Cancel button is clicked
        onNodeWithText("Cancel").performClick()

        // Then callback is not invoked
        verify { onDateChanged.invoke(any()) }.wasNotInvoked()
    }

    @Test
    fun `When Confirm in date picker dialog is clicked Then callback is invoked`() = runComposeUiTest {
        // Given an empty state
        val emptyState = DateSelectorRowState(date = null)

        // When the content is displayed and Select Date button is clicked
        setContent {
            DateSelectorRow(
                state = emptyState,
                onDateChanged = onDateChanged::invoke,
            )
        }
        onNodeWithText("Select Date").performClick()

        // When Confirm button is clicked
        onNodeWithText("OK").performClick()

        // Then callback is invoked with selected date
        verify { onDateChanged.invoke(any()) }.wasInvoked(once)
}

    @Test
    fun `When selected date is tapped Then date picker dialog is shown`() = runComposeUiTest {
    // Given a state with selected date
        val selectedDateState =
            DateSelectorRowState(date = LocalDateTime(2022, 1, 1, 0, 0).toInstant(UtcOffset.ZERO))

        // When the content is displayed and selected date is tapped
        runComposeUiTest {
            setContent {
                DateSelectorRow(
                    state = selectedDateState,
                    onDateChanged = onDateChanged::invoke,
                )
            }
        }
        onNodeWithText("2022-01-01T00:00").performClick()

        // Then date picker dialog is shown
        onNodeWithText("Select date")
            .assertExists()
        onNodeWithText("Cancel")
            .assertExists()
        onNodeWithText("OK")
            .assertExists()
    }

    @Test
    fun `When date is selected Then display date and Clear button`() = runComposeUiTest {
        // Given a state with selected date
        val selectedDateState =
            DateSelectorRowState(date = LocalDateTime(2022, 1, 1, 0, 0).toInstant(UtcOffset.ZERO))

        // When the content is displayed
        setContent {
            DateSelectorRow(
                state = selectedDateState,
                onDateChanged = onDateChanged::invoke,
            )
        }

        // Then UI displays selected date and Clear button
        onNodeWithText("2022-01-01T00:00")
            .assertExists()
        onNodeWithText("Clear")
            .assertExists()
    }

    @Test
    fun `When Clear button is clicked Then invoke callback with null`() = runComposeUiTest {
        // Given a state with selected date
        val selectedDateState =
            DateSelectorRowState(date = LocalDateTime(2022, 1, 1, 0, 0).toInstant(UtcOffset.ZERO))

        // When the content is displayed and Clear button is clicked
        setContent {
            DateSelectorRow(
                state = selectedDateState,
                onDateChanged = onDateChanged::invoke,
            )
        }
        onNodeWithText("Clear").performClick()

        // Then callback is invoked with null
        verify { onDateChanged.invoke(null) }.wasInvoked(once)
    }
    TODO: Replace with mockative for multiplatform support
 */
}
