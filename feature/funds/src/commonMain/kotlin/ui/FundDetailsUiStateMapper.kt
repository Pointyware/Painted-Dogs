package org.pointyware.painteddogs.feature.funds.ui

import org.pointyware.painteddogs.core.common.Mapper
import org.pointyware.painteddogs.feature.funds.viewmodels.FundDetailsUiState

/**
 * TODO: describe purpose/intent of FundDetailsUiStateMapper
 */
object FundDetailsUiStateMapper: Mapper<FundDetailsUiState, FundDetailsViewState> {
    override fun map(input: FundDetailsUiState): FundDetailsViewState {
        return FundDetailsViewState(
            title = input.title,
            description = input.description,
        )
    }
}
