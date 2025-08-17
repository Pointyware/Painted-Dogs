package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * TODO: describe purpose/intent of ContributionDetailsViewModel
 */
@OptIn(ExperimentalUuidApi::class)
interface ContributionDetailsViewModel {
    fun onConfirm(uuid: Uuid)

    val state: StateFlow<ContributionDetailsUiState>
}

@OptIn(ExperimentalUuidApi::class)
class ContributionDetailsViewModelImpl(): ContributionDetailsViewModel {
    private val mutableState = MutableStateFlow(ContributionDetailsUiState())
    override val state: StateFlow<ContributionDetailsUiState>
        get() = mutableState

    override fun onConfirm(uuid: Uuid) {
        TODO("Not yet implemented")
    }
}
