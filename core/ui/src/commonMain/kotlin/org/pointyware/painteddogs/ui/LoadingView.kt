package org.pointyware.painteddogs.core.ui.org.pointyware.painteddogs.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 *
 */
@Composable
fun <T> LoadingView(
    state: LoadingUiState<T>,
    content: @Composable (T)->Unit,
    onClearError: ()->Unit,
) {
    when (state) {
        is LoadingUiState.Loading -> {
            Text(text = "Loading")
            // TODO: create loading graphic
        }
        is LoadingUiState.Success -> content(state.data)
        is LoadingUiState.Failure-> {
            ErrorDialog(
                throwable = state.throwable,
                onDismissRequest = onClearError
            )
        }
    }
}
