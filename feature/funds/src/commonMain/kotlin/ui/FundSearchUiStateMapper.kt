package org.pointyware.painteddogs.feature.funds.ui

import org.pointyware.painteddogs.core.common.Mapper
import org.pointyware.painteddogs.feature.funds.viewmodels.FundSearchUiState
import ui.FundSearchViewState

/**
 * TODO: describe purpose/intent of FundSearchUiStateMapper
 */
object FundSearchUiStateMapper: Mapper<FundSearchUiState, FundSearchViewState> {
    override fun map(input: FundSearchUiState): FundSearchViewState {
        return FundSearchViewState(
            input.query
        )
    }
}
