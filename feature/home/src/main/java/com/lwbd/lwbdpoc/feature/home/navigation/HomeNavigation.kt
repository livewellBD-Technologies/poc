package com.lwbd.lwbdpoc.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.lwbd.lwbdpoc.feature.home.HomeRoute
import kotlin.reflect.KFunction1


const val HOME_ROUTE = "home_route"
private const val DEEP_LINK_URI_PATTERN =
    "https://www.livewellbd.com/home"

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(HOME_ROUTE, navOptions)

fun NavGraphBuilder.homeScreen(onCallNowClick: KFunction1<NavOptions?, Unit>) {
    composable(
        route = HOME_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        ),

    ) {
        HomeRoute(onCallNowClick = onCallNowClick)
    }
}
