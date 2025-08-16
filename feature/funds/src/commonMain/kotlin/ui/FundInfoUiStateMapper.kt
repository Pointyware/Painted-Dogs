package org.pointyware.painteddogs.feature.funds.ui

import org.pointyware.painteddogs.core.common.Mapper
import org.pointyware.painteddogs.feature.funds.viewmodels.FundInfoUiState

/**
 * TODO: describe purpose/intent of FundInfoUiStateMapper
 */
object FundInfoUiStateMapper: Mapper<FundInfoUiState, FundInfoScreenState> {
    override fun map(input: FundInfoUiState): FundInfoScreenState {
        return FundInfoScreenState(
            input.id,
            input.title
        )
    }
}
