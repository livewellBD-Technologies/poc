package com.lwbd.lwbdpoc.feature.menu.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lwbd.lwbdpoc.feature.menu.MenuRoute
import kotlin.reflect.KFunction1

//const val TOPIC_ID_ARG = "topicId"
const val MENU_ROUTE_BASE = "menu_route"
const val MENU_ROUTE = MENU_ROUTE_BASE

fun NavController.navigateToMenu( navOptions: NavOptions? = null) {
    val route =
        MENU_ROUTE_BASE

    navigate(route, navOptions)
}

fun NavGraphBuilder.menuScreen(
    onNavigationClick: KFunction1<NavOptions?, Unit>,
) {
    composable(
        route = MENU_ROUTE,

    ) {
        MenuRoute(onTopicClick = onNavigationClick)
    }
}
