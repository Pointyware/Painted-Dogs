package org.pointyware.painteddogs.feature.funds.viewmodels

data class CollectionSearchUiState(
    val query: String,
    val loading: Boolean,
    val result: List<FundUiState>
) {
    companion object {
        val EMPTY = CollectionSearchUiState("", false, emptyList())
    }
}
