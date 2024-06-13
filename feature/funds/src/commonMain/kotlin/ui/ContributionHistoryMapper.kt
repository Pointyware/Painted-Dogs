package org.pointyware.painteddogs.feature.collections.core.ui

import org.pointyware.painteddogs.feature.collections.core.FundRowState
import org.pointyware.painteddogs.feature.collections.core.viewmodels.DonationHistoryUiState

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
