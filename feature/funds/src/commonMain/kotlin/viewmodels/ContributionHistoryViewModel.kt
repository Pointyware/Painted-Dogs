package org.pointyware.painteddogs.feature.funds.viewmodels

import kotlinx.coroutines.flow.StateFlow
import org.pointyware.painteddogs.core.entities.Uuid

/**
 */
interface ContributionHistoryViewModel {
    val state: StateFlow<DonationHistoryUiState>
    fun onViewFund(fundId: Uuid)
}

data class DonationHistoryUiState(
    val contributions: List<DonationUiState> // TODO: replace with complete data
)

data class DonationUiState(
    val id: Uuid,
    val title: String,
    val description: String,
)

class ContributionHistoryViewModelImpl(): ContributionHistoryViewModel {
    override val state: StateFlow<DonationHistoryUiState>
        get() = TODO("Not yet implemented")

    override fun onViewFund(fundId: Uuid) {
        TODO("Not yet implemented")
    }
}
