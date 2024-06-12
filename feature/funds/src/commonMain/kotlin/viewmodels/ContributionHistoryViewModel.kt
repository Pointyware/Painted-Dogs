package org.pointyware.painteddogs.feature.collections.core.viewmodels

import kotlinx.coroutines.flow.StateFlow

/**
 */
interface ContributionHistoryViewModel {
    val state: StateFlow<DonationHistoryUiState>
    fun onViewFund(fundId: String)
}

data class DonationHistoryUiState(
    val contributions: List<DonationUiState> // TODO: replace with complete data
)

data class DonationUiState(
    val id: String,
    val title: String,
    val description: String,
)

class ContributionHistoryViewModelImpl(): ContributionHistoryViewModel {
    override val state: StateFlow<DonationHistoryUiState>
        get() = TODO("Not yet implemented")

    override fun onViewFund(fundId: String) {
        TODO("Not yet implemented")
    }
}
