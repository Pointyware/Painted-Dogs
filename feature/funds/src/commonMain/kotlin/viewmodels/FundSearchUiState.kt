package org.pointyware.painteddogs.feature.funds.viewmodels

/**
 * TODO: describe purpose/intent of FundSearchUiState
 */
data class FundSearchUiState(
    val query: String,
    val loading: Boolean,
    val result: List<FundUiState>
) {
    companion object {
        val EMPTY = FundSearchUiState("", false, emptyList())
    }
}
