package org.pointyware.painteddogs.feature.collections.core.viewmodels

data class CollectionSearchUiState(
    val query: String,
    val loading: Boolean,
    val result: List<CollectionUiState>
)
