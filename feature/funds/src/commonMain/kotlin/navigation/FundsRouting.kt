package org.pointyware.painteddogs.feature.funds.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.Destination
import org.pointyware.painteddogs.feature.funds.ContributionDetailsScreen
import org.pointyware.painteddogs.feature.funds.ContributionInfoScreen
import org.pointyware.painteddogs.feature.funds.di.FundDependencies
import org.pointyware.painteddogs.feature.funds.ui.FundDetailsScreen
import org.pointyware.painteddogs.feature.funds.ui.FundInfoScreen
import ui.FundSearchView
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data object Funds {
    @Serializable
    data object Create

    @Serializable
    data object Search

}

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class FundInfo(val fundId: String): Destination {
    constructor(id: Uuid): this(id.toString())
}

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class FundDetails(val fundId: String) {
    constructor(id: Uuid): this(id.toString())
}

@OptIn(ExperimentalUuidApi::class)
@Serializable
class FundContributions(val fundId: String) { // TODO: add path to view all contributions from FundInfo
    constructor(id: Uuid): this(id.toString())
}

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class ContributionDetails(
    val contribId: String
) {
    constructor(id: Uuid): this(id.toString())
}

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class ContributionInfo(
    val contribId: String
) {
    constructor(id: Uuid): this(id.toString())
}

/**
 *
 */
@OptIn(ExperimentalUuidApi::class)
fun NavGraphBuilder.fundsRouting(
    navController: NavController
) {

    // we need to make a collection before anyone can contribute
    composable<Funds.Create> {
        val di = remember { getKoin() }
        val fundsDependencies = remember { di.get<FundDependencies>() }

        val detailViewModel = remember { fundsDependencies.getFundDetailsViewModel() }
        val mapper = remember { fundsDependencies.getFundDetailsUiStateMapper() }
        val state = detailViewModel.state.collectAsState()
        LaunchedEffect(Unit) {
            detailViewModel.onBack.collect {
                navController.popBackStack()
            }
        }
        FundDetailsScreen(
            state = mapper.map(state.value),
            onConfirm = detailViewModel::onConfirm,
        )
    }
    // a user needs to find a fund to contribute to
    composable<Funds.Search> {
        val di = remember { getKoin() }
        val fundsDependencies = remember { di.get<FundDependencies>() }

        val searchViewModel = remember { fundsDependencies.getFundSearchViewModel() }
        val mapper = remember { fundsDependencies.getFundSearchUiStateMapper() }
        val state = searchViewModel.state.collectAsState()
        FundSearchView(
            state = mapper.map(state.value),
            onSearchQueryChanged = searchViewModel::onSearchQueryChanged,
            onSearchQuerySubmitted = searchViewModel::onSubmitQuery,
            onFundSelected = { fundId -> navController.navigate(FundInfo(fundId)) },
        )
    }
    // a user needs to see the details of a fund before deciding to contribute
    composable<FundInfo> {
        val info = it.toRoute<FundInfo>()
        val di = remember { getKoin() }
        val fundsDependencies = remember { di.get<FundDependencies>() }

        val fundInfoViewModel = remember { fundsDependencies.getFundInfoViewModel() }
        val mapper = remember { fundsDependencies.getFundInfoUiStateMapper() }
        val state = fundInfoViewModel.state.collectAsState()
        FundInfoScreen(
            state = mapper.map(state.value),
            onContribute = { fundId -> navController.navigate(FundDetails(fundId)) },
        )
    }
    // a user needs to determine how much they want to contribute
    composable<ContributionDetails> {
        val details = it.toRoute<ContributionDetails>()
        val di = remember { getKoin() }
        val fundsDependencies = remember { di.get<FundDependencies>() }

        val contributionDetailsViewModel = remember { fundsDependencies.getContributionDetailsViewModel() }
        val mapper = remember { fundsDependencies.getContributionDetailsUiStateMapper() }
        val state = contributionDetailsViewModel.state.collectAsState()
        ContributionDetailsScreen(
            state = mapper.map(state.value),
            onConfirm = contributionDetailsViewModel::onConfirm,
        )
    }
    // we need to show the user the details of their contribution after server confirmation
    composable<ContributionInfo> {
        val info = it.toRoute<ContributionInfo>()
        val di = remember { getKoin() }
        val fundsDependencies = remember { di.get<FundDependencies>() }

        val contributionInfoScreen = remember { fundsDependencies.getContributionInfoViewModel() }
        val mapper = remember { fundsDependencies.getContributionInfoUiStateMapper() }
        val state = contributionInfoScreen.state.collectAsState()
        ContributionInfoScreen(
            state = mapper.map(state.value),
        )
    }
}
