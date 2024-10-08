package com.lwbd.lwbdpoc.appointment_booking.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lwbd.lwbdpoc.appointment_booking.AppointmentBookingRoute

const val APPOINTMENT_BOOKING_ROUTE_BASE = "appointment_booking_route"


fun NavController.navigateToAppointmentBooking(navOptions: NavOptions? = null) {
    val route =
        APPOINTMENT_BOOKING_ROUTE_BASE
    navigate(route, navOptions)
}

fun NavGraphBuilder.appointmentBookingScreen(
    navController: NavHostController,

    ) {
    composable(
        route = APPOINTMENT_BOOKING_ROUTE_BASE,

        ) {
        AppointmentBookingRoute(
            navController = navController
        )
    }
}
