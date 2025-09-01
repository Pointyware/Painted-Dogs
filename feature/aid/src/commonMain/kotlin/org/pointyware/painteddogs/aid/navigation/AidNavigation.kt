package org.pointyware.painteddogs.aid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import org.pointyware.painteddogs.aid.entities.Resource

fun NavController.navigateToSupportDetail(requestId: String) {
    navigate(AidDestination.SupportDetail(requestId))
}

fun NavController.navigateToClaimDetail(offerId: String) {
    navigate(AidDestination.ClaimDetail(offerId))
}

fun NavController.navigateToRequestDetail(requestId: String) {
    navigate(AidDestination.RequestDetail(requestId)) {
        popUpTo(graph.findStartDestination().id)
    }
}

fun NavController.navigateToOfferDetail(offerId: String) {
    navigate(AidDestination.OfferDetail(offerId)) {
        popUpTo(graph.findStartDestination().id)
    }
}

fun NavController.navigateToOfferDraft(resource: Resource) {
    navigate(AidDestination.DraftOffer(resource)) {
        launchSingleTop = true
        restoreState = true
        popUpTo(route = AidDestination.Board)
    }
}
