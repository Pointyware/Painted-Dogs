package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Represents a search result for a collection, localized to the search view.
 */
@OptIn(ExperimentalUuidApi::class)
data class FundUiState(
    val id: Uuid,
    val title: String,
    val description: String,
    val progress: ProgressUiState
)
