package org.pointyware.painteddogs.feature.funds.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.core.navigation.Route
import org.pointyware.painteddogs.core.navigation.route
import org.pointyware.painteddogs.feature.funds.ContributionDetailsScreen
import org.pointyware.painteddogs.feature.funds.ContributionInfoScreen
import org.pointyware.painteddogs.feature.funds.di.FundDependencies
import org.pointyware.painteddogs.feature.funds.ui.FundDetailsScreen
import org.pointyware.painteddogs.feature.funds.ui.FundInfoScreen
import ui.FundSearchView

val fundsRoute = route("funds")
val createFundsRoute = route("funds", "create")
val searchFundsRoute = route("funds", "search")
val fundDetailsRoute = route("funds", "\$collectionId")
val contributeRoute = route("funds", "\$collectionId", "contribute")
val viewContributionRoute = route("funds", "\$collectionId", "contributions", "\$contributionId")

/**
 *
 */
@Composable
fun LocationRootScope<Route<String>, Any>.fundsRouting(
) {
    val di = remember { getKoin() }
    val fundsDependencies = remember { di.get<FundDependencies>() }

    // we need to make a collection before anyone can contribute
    location(createFundsRoute) { navController ->

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
    location(searchFundsRoute) {
        val searchViewModel = remember { fundsDependencies.getFundSearchViewModel() }
        val mapper = remember { fundsDependencies.getFundSearchUiStateMapper() }
        val state = searchViewModel.state.collectAsState()
        FundSearchView(
            state = mapper.map(state.value),
            onSearchQueryChanged = searchViewModel::onSearchQueryChanged,
            onSearchQuerySubmitted = searchViewModel::onSubmitQuery,
            onFundSelected = { uuid -> it.navigateTo(route("funds", "$uuid")) },
        )
    }
    // a user needs to see the details of a collection before deciding to contribute
    location(fundDetailsRoute) {
        val fundInfoViewModel = remember { fundsDependencies.getFundInfoViewModel() }
        val mapper = remember { fundsDependencies.getFundInfoUiStateMapper() }
        val state = fundInfoViewModel.state.collectAsState()
        FundInfoScreen(
            state = mapper.map(state.value),
            onContribute = { uuid -> it.navigateTo(route("funds", "$uuid", "contribute")) },
        )
    }
    // a user needs to determine how much they want to contribute
    location(contributeRoute) {
        val contributionDetailsViewModel = remember { fundsDependencies.getContributionDetailsViewModel() }
        val mapper = remember { fundsDependencies.getContributionDetailsUiStateMapper() }
        val state = contributionDetailsViewModel.state.collectAsState()
        ContributionDetailsScreen(
            state = mapper.map(state.value),
            onConfirm = { uuid -> it.navigateTo(route("funds", "$uuid", "contribute", "confirm")) },
        )
    }
    // we need to show the user the details of their contribution after server confirmation
    location(viewContributionRoute) {
        val contributionInfoScreen = remember { fundsDependencies.getContributionInfoViewModel() }
        val mapper = remember { fundsDependencies.getContributionInfoUiStateMapper() }
        val state = contributionInfoScreen.state.collectAsState()
        ContributionInfoScreen(
            state = mapper.map(state.value),
        )
    }
}
