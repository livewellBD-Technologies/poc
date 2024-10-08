package com.lwbd.lwbdpoc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.lwbd.lwbdpoc.appointment_booking.navigation.appointmentBookingScreen
import com.lwbd.lwbdpoc.appointment_booking.navigation.navigateToAppointmentBooking
import com.lwbd.lwbdpoc.feature.finddoctor.navigation.findDoctorScreen
import com.lwbd.lwbdpoc.feature.home.navigation.HOME_ROUTE
import com.lwbd.lwbdpoc.feature.home.navigation.homeScreen
import com.lwbd.lwbdpoc.feature.menu.navigation.menuScreen
import com.lwbd.lwbdpoc.feature.menu.navigation.navigateToMenu
import com.lwbd.lwbdpoc.feature.prescriptions.navigation.prescriptionsScreen
import com.lwbd.lwbdpoc.ui.LwbdAppState


/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun LwbdNavHost(
    appState: LwbdAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(onCallNowClick = navController::navigateToAppointmentBooking)
        findDoctorScreen(
            onNavigationClick = navController::navigateToMenu,
            onShowSnackbar = onShowSnackbar,
        )
        prescriptionsScreen(
            onNavigationClick = navController::navigateToMenu,
            onShowSnackbar = onShowSnackbar,
        )
        menuScreen(
            onNavigationClick = navController::navigateToMenu,
        )
        appointmentBookingScreen(
            navController = navController,
        )
    }
}
