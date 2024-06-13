package org.pointyware.painteddogs.feature.funds.ui

import org.pointyware.painteddogs.feature.funds.viewmodels.DonationHistoryUiState

/**
 *
 */
object ContributionHistoryMapper {
    fun map(value: DonationHistoryUiState): ContributionHistoryScreenState {
        return ContributionHistoryScreenState(
            value.contributions.map {
                FundRowState(
                    it.id,
                    it.title,
                    it.description
                )
            }
        )
    }
}
