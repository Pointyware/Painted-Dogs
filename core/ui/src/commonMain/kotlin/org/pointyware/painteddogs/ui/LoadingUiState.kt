package org.pointyware.painteddogs.core.ui.org.pointyware.painteddogs.ui

/**
 * Matches the [Result] API in terms of nomenclature.
 *
 * Could possibly be replaced with `Result<FeatureUiState?>` where the null value
 * extends the feature ui state to indicate unknown/loading state.
 *
 * See bridging function: [toUiState]
 */
sealed interface LoadingUiState {
    data object Loading: LoadingUiState

    data class Success<T>(
        val data: T
    ): LoadingUiState

    data class Failure(
        val throwable: Throwable
    ): LoadingUiState

}

/**
 *
 */
fun <T: Any> Result<T>?.toUiState(): LoadingUiState = when {
    this == null -> LoadingUiState.Loading
    this.isSuccess -> LoadingUiState.Success(this.getOrThrow())
    else -> LoadingUiState.Failure(this.exceptionOrNull()!!)
}
