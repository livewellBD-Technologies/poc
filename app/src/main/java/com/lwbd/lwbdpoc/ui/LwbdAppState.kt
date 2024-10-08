package com.lwbd.lwbdpoc.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.lwbd.lwbdpoc.core.data.util.NetworkMonitor
import com.lwbd.lwbdpoc.core.data.util.TimeZoneMonitor
import com.lwbd.lwbdpoc.core.ui.TrackDisposableJank
import com.lwbd.lwbdpoc.feature.finddoctor.navigation.FIND_DOCTOR_ROUTE
import com.lwbd.lwbdpoc.feature.finddoctor.navigation.navigateToFindDoctor
import com.lwbd.lwbdpoc.feature.home.navigation.HOME_ROUTE
import com.lwbd.lwbdpoc.feature.home.navigation.navigateToHome
import com.lwbd.lwbdpoc.feature.menu.navigation.MENU_ROUTE
import com.lwbd.lwbdpoc.feature.menu.navigation.navigateToMenu
import com.lwbd.lwbdpoc.feature.prescriptions.navigation.PRESCRIPTIONS_ROUTE
import com.lwbd.lwbdpoc.feature.prescriptions.navigation.navigateToPrescriptions
import com.lwbd.lwbdpoc.navigation.TopLevelDestination
import com.lwbd.lwbdpoc.navigation.TopLevelDestination.FIND_DOCTOR
import com.lwbd.lwbdpoc.navigation.TopLevelDestination.HOME
import com.lwbd.lwbdpoc.navigation.TopLevelDestination.MENU
import com.lwbd.lwbdpoc.navigation.TopLevelDestination.PRESCRIPTIONS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.TimeZone

@Composable
fun rememberLwbdAppState(
    networkMonitor: NetworkMonitor,
    timeZoneMonitor: TimeZoneMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): LwbdAppState {
    NavigationTrackingSideEffect(navController)
    return remember(
        navController,
        coroutineScope,
        networkMonitor,
        timeZoneMonitor,
    ) {
        LwbdAppState(
            navController = navController,
            coroutineScope = coroutineScope,
            networkMonitor = networkMonitor,
            timeZoneMonitor = timeZoneMonitor,
        )
    }
}

@Stable
class LwbdAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor,
    timeZoneMonitor: TimeZoneMonitor,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HOME_ROUTE -> HOME
            FIND_DOCTOR_ROUTE -> FIND_DOCTOR
            PRESCRIPTIONS_ROUTE -> PRESCRIPTIONS
            MENU_ROUTE -> MENU
            else -> null
        }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    val currentTimeZone = timeZoneMonitor.currentTimeZone
        .stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5_000),
            TimeZone.currentSystemDefault(),
        )

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // re-selecting the same item
                launchSingleTop = true
                // Restore state when re-selecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                HOME -> navController.navigateToHome(topLevelNavOptions)
                FIND_DOCTOR -> navController.navigateToFindDoctor(topLevelNavOptions)
                PRESCRIPTIONS -> navController.navigateToPrescriptions(topLevelNavOptions)
                MENU -> navController.navigateToMenu(topLevelNavOptions)
            }
        }
    }

    fun navigateToMenu() = navController.navigateToMenu()
}

/**
 * Stores information about navigation events to be used with JankStats
 */
@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}
