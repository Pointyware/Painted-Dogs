package org.pointyware.painteddogs.core.ui.org.pointyware.painteddogs.ui

/**
 * Matches the [Result] API in terms of nomenclature.
 *
 * Could possibly be replaced with `Result<FeatureUiState?>` where the null value
 * extends the feature ui state to indicate unknown/loading state.
 *
 * See bridging function: [toUiState]
 */
sealed interface LoadingUiState<T> {
    class Loading<T>: LoadingUiState<T>

    data class Success<T>(
        val data: T
    ): LoadingUiState<T>

    data class Failure<T>(
        val throwable: Throwable
    ): LoadingUiState<T>

}

/**
 *
 */
fun <T: Any> Result<T>?.toUiState(): LoadingUiState<T> = when {
    this == null -> LoadingUiState.Loading()
    this.isSuccess -> LoadingUiState.Success(this.getOrThrow())
    else -> LoadingUiState.Failure(this.exceptionOrNull()!!)
}
