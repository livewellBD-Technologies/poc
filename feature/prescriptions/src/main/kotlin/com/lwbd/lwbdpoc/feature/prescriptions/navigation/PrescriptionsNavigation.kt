

package com.lwbd.lwbdpoc.feature.prescriptions.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lwbd.lwbdpoc.feature.prescriptions.PrescriptionsRoute
import kotlin.reflect.KFunction1

const val PRESCRIPTIONS_ROUTE = "prescriptions_route"

fun NavController.navigateToPrescriptions(navOptions: NavOptions) = navigate(PRESCRIPTIONS_ROUTE, navOptions)

fun NavGraphBuilder.prescriptionsScreen(
    onNavigationClick: KFunction1<NavOptions?, Unit>,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = PRESCRIPTIONS_ROUTE) {
        PrescriptionsRoute()
    }
}
