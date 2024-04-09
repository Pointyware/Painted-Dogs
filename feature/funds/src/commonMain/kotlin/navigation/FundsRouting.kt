package org.pointyware.painteddogs.feature.funds.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.core.navigation.TypeRouterScope
import org.pointyware.painteddogs.feature.funds.ContributionDetailsScreen
import org.pointyware.painteddogs.feature.funds.ContributionInfoScreen
import org.pointyware.painteddogs.feature.funds.di.FundDependencies
import org.pointyware.painteddogs.feature.funds.ui.FundDetailsScreen
import org.pointyware.painteddogs.feature.funds.ui.FundInfoScreen
import ui.FundSearchView

object Funds {
    object Create
    object Search
    data class FundDetails(val collectionId: Uuid)
    data class ContributionDetails(val fundId: Uuid)
    data class ContributionInfo(val fundId: Uuid, val contributionId: Uuid)
}
/**
 *
 */
@Composable
fun TypeRouterScope.fundsRouting(
) {
    val di = remember { getKoin() }
    val fundsDependencies = remember { di.get<FundDependencies>() }

    // we need to make a collection before anyone can contribute
    location(Funds.Create::class) { navController, _ ->

        val detailViewModel = remember { fundsDependencies.getFundDetailsViewModel() }
        val mapper = remember { fundsDependencies.getFundDetailsUiStateMapper() }
        val state = detailViewModel.state.collectAsState()
        LaunchedEffect(Unit) {
            detailViewModel.onBack.collect {
                navController.goBack()
            }
        }
        FundDetailsScreen(
            state = mapper.map(state.value),
            onConfirm = detailViewModel::onConfirm,
        )
    }
    // a user needs to find a collection to contribute to
    location(Funds.Search::class) { navController, _ ->
        val searchViewModel = remember { fundsDependencies.getFundSearchViewModel() }
        val mapper = remember { fundsDependencies.getFundSearchUiStateMapper() }
        val state = searchViewModel.state.collectAsState()
        FundSearchView(
            state = mapper.map(state.value),
            onSearchQueryChanged = searchViewModel::onSearchQueryChanged,
            onSearchQuerySubmitted = searchViewModel::onSubmitQuery,
            onFundSelected = { uuid ->
                val arg = Funds.FundDetails(uuid)
                navController.navigateTo(Funds.FundDetails::class, arg)
            },
        )
    }
    // a user needs to see the details of a collection before deciding to contribute
    location(Funds.FundDetails::class) { navController, _ ->
        val fundInfoViewModel = remember { fundsDependencies.getFundInfoViewModel() }
        val mapper = remember { fundsDependencies.getFundInfoUiStateMapper() }
        val state = fundInfoViewModel.state.collectAsState()
        FundInfoScreen(
            state = mapper.map(state.value),
            onContribute = { uuid ->
                val arg = Funds.ContributionDetails(fundId = uuid)
                navController.navigateTo(Funds.ContributionDetails::class, arg)
            },
        )
    }
    // a user needs to determine how much they want to contribute
    location(Funds.ContributionDetails::class) { navController, details ->
        val contributionDetailsViewModel = remember { fundsDependencies.getContributionDetailsViewModel() }
        val mapper = remember { fundsDependencies.getContributionDetailsUiStateMapper() }
        val state = contributionDetailsViewModel.state.collectAsState()
        ContributionDetailsScreen(
            state = mapper.map(state.value),
            onConfirm = contributionDetailsViewModel::onConfirm,
        )
    }
    // we need to show the user the details of their contribution after server confirmation
    location(Funds.ContributionInfo::class) { navController, info ->
        val contributionInfoScreen = remember { fundsDependencies.getContributionInfoViewModel() }
        val mapper = remember { fundsDependencies.getContributionInfoUiStateMapper() }
        val state = contributionInfoScreen.state.collectAsState()
        ContributionInfoScreen(
            state = mapper.map(state.value),
        )
    }
}
