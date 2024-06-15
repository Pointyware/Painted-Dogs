package org.pointyware.painteddogs.feature.funds.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.feature.funds.ContributionDetailsScreen
import org.pointyware.painteddogs.feature.funds.ContributionInfoScreen
import org.pointyware.painteddogs.feature.funds.di.FundDependencies
import org.pointyware.painteddogs.feature.funds.ui.FundDetailsScreen
import org.pointyware.painteddogs.feature.funds.ui.FundInfoScreen
import ui.FundSearchView

/**
 *
 */
@Composable
fun LocationRootScope<String?, Any>.fundsRouting(
) {
    val di = remember { getKoin() }
    val fundsDependencies = remember { di.get<FundDependencies>() }

    // we need to make a collection before anyone can contribute
    location("funds/create") {

        val detailViewModel = remember { fundsDependencies.getFundDetailsViewModel() }
        val mapper = remember { fundsDependencies.getFundDetailsUiStateMapper() }
        val state = detailViewModel.state.collectAsState()
        FundDetailsScreen(
            state = mapper.map(state.value),
            onConfirm = { it.navigateTo("collections/123/contribute") },
        )
    }
    // a user needs to find a collection to contribute to
    location("funds/search") {
        val searchViewModel = remember { fundsDependencies.getFundSearchViewModel() }
        val mapper = remember { fundsDependencies.getFundSearchUiStateMapper() }
        val state = searchViewModel.state.collectAsState()
        FundSearchView(
            state = mapper.map(state.value),
            onSearchQueryChanged = searchViewModel::onSearchQueryChanged,
            onSearchQuerySubmitted = searchViewModel::onSubmitQuery,
            onFundSelected = { uuid -> it.navigateTo("funds/$uuid") },
        )
    }
    // a user needs to see the details of a collection before deciding to contribute
    location("funds/\$collectionId") {
        val fundInfoViewModel = remember { fundsDependencies.getFundInfoViewModel() }
        val mapper = remember { fundsDependencies.getFundInfoUiStateMapper() }
        val state = fundInfoViewModel.state.collectAsState()
        FundInfoScreen(
            state = mapper.map(state.value),
            onContribute = { uuid -> it.navigateTo("funds/$uuid/contribute") },
        )
    }
    // a user needs to determine how much they want to contribute
    location("funds/\$collectionId/contribute") {
        val contributionDetailsViewModel = remember { fundsDependencies.getContributionDetailsViewModel() }
        val mapper = remember { fundsDependencies.getContributionDetailsUiStateMapper() }
        val state = contributionDetailsViewModel.state.collectAsState()
        ContributionDetailsScreen(
            state = mapper.map(state.value),
            onConfirm = { uuid -> it.navigateTo("funds/$uuid/contribute/confirm") },
        )
    }
    // we need to show the user the details of their contribution after server confirmation
    location("funds/\$collectionId/contributions/\$contributionId") {
        val contributionInfoScreen = remember { fundsDependencies.getContributionInfoViewModel() }
        val mapper = remember { fundsDependencies.getContributionInfoUiStateMapper() }
        val state = contributionInfoScreen.state.collectAsState()
        ContributionInfoScreen(
            state = mapper.map(state.value),
        )
    }
}
