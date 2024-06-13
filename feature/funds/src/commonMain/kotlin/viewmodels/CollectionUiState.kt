package org.pointyware.painteddogs.feature.funds.viewmodels

import org.pointyware.painteddogs.core.entities.Uuid

/**
 * Represents a search result for a collection, localized to the search view.
 */
data class CollectionUiState(
    val id: Uuid,
    val title: String,
    val description: String,
    val progress: ProgressUiState
)
