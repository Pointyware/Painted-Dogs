package org.pointyware.painteddogs.feature.collections.core.navigation

import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.core.ui.ContributionDetailsScreen
import org.pointyware.painteddogs.core.ui.ContributionDetailsScreenState
import org.pointyware.painteddogs.core.ui.ContributionInfoScreen
import org.pointyware.painteddogs.core.ui.ContributionInfoScreenState
import org.pointyware.painteddogs.core.ui.FundDetailsScreen
import org.pointyware.painteddogs.core.ui.FundInfoScreen
import org.pointyware.painteddogs.core.ui.FundInfoScreenState
import org.pointyware.painteddogs.core.ui.SearchCollectionsScreen
import org.pointyware.painteddogs.core.ui.SearchCollectionsScreenState
import org.pointyware.painteddogs.core.ui.views.FundDetailsViewState

/**
 *
 */
fun LocationRootScope<String?>.fundsRouting(
    onContribute: () -> Unit,
    onConfirmFundDetails: () -> Unit,
    onConfirmContributionDetails: () -> Unit,

) {

    // we need to make a collection before anyone can contribute
    location("funds/create") {

        FundDetailsScreen(
            state = FundDetailsViewState(
                title = "My Collection",
                description = "A collection of things",
            ),
            onConfirm = onConfirmFundDetails,
        )
    }
    // a user needs to find a collection to contribute to
    location("funds/search") {
        SearchCollectionsScreen(
            state = SearchCollectionsScreenState(
                query = "",
            ),
        )
    }
    // a user needs to see the details of a collection before deciding to contribute
    location("funds/\$collectionId") {
        FundInfoScreen(
            state = FundInfoScreenState(
                title = "My Collection",
            ),
            onContribute = onContribute,
        )
    }
    // a user needs to determine how much they want to contribute
    location("funds/\$collectionId/contribute") {
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
        ContributionInfoScreen(
            state = ContributionInfoScreenState(
                id = "123",
                title = "My Collection",
                description = "A collection of things",
            ),
        )
    }
}
