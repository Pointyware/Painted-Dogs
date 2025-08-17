package org.pointyware.painteddogs.feature.funds.ui

import org.pointyware.painteddogs.feature.funds.viewmodels.DonationHistoryUiState
import kotlin.uuid.ExperimentalUuidApi

/**
 *
 */
@OptIn(ExperimentalUuidApi::class)
object ContributionHistoryUiStateMapper {
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
