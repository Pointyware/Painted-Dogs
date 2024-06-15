package org.pointyware.painteddogs.feature.funds.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.feature.funds.ContributionDetailsScreen
import org.pointyware.painteddogs.feature.funds.ContributionDetailsScreenState
import org.pointyware.painteddogs.feature.funds.ContributionInfoScreen
import org.pointyware.painteddogs.feature.funds.ContributionInfoScreenState
import org.pointyware.painteddogs.feature.funds.di.FundDependencies
import org.pointyware.painteddogs.feature.funds.ui.FundDetailsScreen
import org.pointyware.painteddogs.feature.funds.ui.FundInfoScreen
import org.pointyware.painteddogs.feature.funds.ui.FundInfoScreenState
import org.pointyware.painteddogs.feature.funds.ui.SearchCollectionsScreen
import org.pointyware.painteddogs.feature.funds.ui.SearchCollectionsScreenState

/**
 *
 */
@Composable
fun LocationRootScope<String?, Any>.fundsRouting(
    onContribute: () -> Unit,
    onConfirmFundDetails: () -> Unit,
    onConfirmContributionDetails: () -> Unit,
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
            onConfirm = onConfirmFundDetails,
        )
    }
    // a user needs to find a collection to contribute to
    location("funds/search") {
        // TODO: inject view models/mapper
        SearchCollectionsScreen(
            state = SearchCollectionsScreenState(
                query = "",
            ),
        )
    }
    // a user needs to see the details of a collection before deciding to contribute
    location("funds/\$collectionId") {
        // TODO: inject view models/mapper
        FundInfoScreen(
            state = FundInfoScreenState(
                title = "My Collection",
            ),
            onContribute = onContribute,
        )
    }
    // a user needs to determine how much they want to contribute
    location("funds/\$collectionId/contribute") {
        // TODO: inject view models/mapper
        ContributionDetailsScreen(
            state = ContributionDetailsScreenState(
                id = "123",
                title = "My Collection",
                description = "A collection of things",
            ),
            onConfirm = onConfirmContributionDetails,
        )
    }
    // we need to show the user the details of their contribution after server confirmation
    location("funds/\$collectionId/contributions/\$contributionId") {
        // TODO: inject view models/mapper
        ContributionInfoScreen(
            state = ContributionInfoScreenState(
                id = "123",
                title = "My Collection",
                description = "A collection of things",
            ),
        )
    }
}
