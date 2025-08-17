package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 *
 */
@OptIn(ExperimentalUuidApi::class)
class FundInfoUiState(
    val id: Uuid,
    val title: String
)
