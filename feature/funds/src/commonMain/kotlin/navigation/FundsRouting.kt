package org.pointyware.painteddogs.feature.funds.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.core.navigation.Destination
import org.pointyware.painteddogs.core.navigation.LocationRootScope
import org.pointyware.painteddogs.core.navigation.Path
import org.pointyware.painteddogs.core.navigation.StaticRoute
import org.pointyware.painteddogs.core.navigation.emptyPath
import org.pointyware.painteddogs.core.navigation.pathArgumentPlaceholder
import org.pointyware.painteddogs.feature.funds.ContributionDetailsScreen
import org.pointyware.painteddogs.feature.funds.ContributionInfoScreen
import org.pointyware.painteddogs.feature.funds.di.FundDependencies
import org.pointyware.painteddogs.feature.funds.ui.FundDetailsScreen
import org.pointyware.painteddogs.feature.funds.ui.FundInfoScreen
import ui.FundSearchView
import kotlin.uuid.ExperimentalUuidApi

val fundsRoute = StaticRoute("funds", Unit)
val createFundsRoute = fundsRoute.fixed("create")
val searchFundsRoute = fundsRoute.fixed("search")
@Deprecated("Repalce with Destination")
val fundInfoRoute = fundsRoute.variable<Uuid>("fund-$pathArgumentPlaceholder")
@OptIn(ExperimentalUuidApi::class)
data class FundInfo(val uuid: kotlin.uuid.Uuid): Destination
val contributionsRoute = fundInfoRoute.fixed("contributions")
val contributionInfoRoute = contributionsRoute.variable<Uuid>("contrib-$pathArgumentPlaceholder")
val contributionDetailsRoute = fundInfoRoute.fixed("contribute")

fun getFundsCreationPath() = createFundsRoute.skip { it.skip { emptyPath() }}
fun getFundsSearchPath() = searchFundsRoute.skip { it.skip { emptyPath() }}
@OptIn(ExperimentalUuidApi::class)
fun getFundInfoPath(fundId: kotlin.uuid.Uuid): Destination = FundInfo(fundId)
fun getContributionsPath(fundId: Uuid) = contributionsRoute.skip { it.provide(fundId) { it.skip { emptyPath()}}}
fun getContributionInfoPath(fundId: Uuid, contributionId: Uuid): Path {
    return contributionInfoRoute.provide(contributionId) { contributionsRoute ->
        contributionsRoute.skip { fundRoute ->
            fundRoute.provide(fundId) {
                emptyPath()
            }
        }
    }
}
fun getContributionDetailsPath(fundId: Uuid) = contributionDetailsRoute.skip { it.provide(fundId) { it.skip { emptyPath()}}}

/**
 *
 */
@OptIn(ExperimentalUuidApi::class)
@Composable
fun LocationRootScope<Any, Any>.fundsRouting(
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
            onFundSelected = { fundId -> it.navigateTo(getFundInfoPath(fundId)) },
        )
    }
    // a user needs to see the details of a collection before deciding to contribute
    location(fundInfoRoute) {
        val fundInfoViewModel = remember { fundsDependencies.getFundInfoViewModel() }
        val mapper = remember { fundsDependencies.getFundInfoUiStateMapper() }
        val state = fundInfoViewModel.state.collectAsState()
        FundInfoScreen(
            state = mapper.map(state.value),
            onContribute = { fundId -> it.navigateTo(getContributionDetailsPath(fundId)) },
        )
    }
    // a user needs to determine how much they want to contribute
    location(contributionDetailsRoute) {
        val contributionDetailsViewModel = remember { fundsDependencies.getContributionDetailsViewModel() }
        val mapper = remember { fundsDependencies.getContributionDetailsUiStateMapper() }
        val state = contributionDetailsViewModel.state.collectAsState()
        ContributionDetailsScreen(
            state = mapper.map(state.value),
            onConfirm = contributionDetailsViewModel::onConfirm,
        )
    }
    // we need to show the user the details of their contribution after server confirmation
    location(contributionInfoRoute) {
        val contributionInfoScreen = remember { fundsDependencies.getContributionInfoViewModel() }
        val mapper = remember { fundsDependencies.getContributionInfoUiStateMapper() }
        val state = contributionInfoScreen.state.collectAsState()
        ContributionInfoScreen(
            state = mapper.map(state.value),
        )
    }
}
