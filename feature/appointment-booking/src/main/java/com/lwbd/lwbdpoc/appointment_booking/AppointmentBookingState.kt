package com.lwbd.lwbdpoc.appointment_booking

import com.lwbd.lwbdpoc.apointment_booking.R
import com.lwbd.lwbdpoc.core.base.BaseState

data class AppointmentBookingState(
    val showSheet: Boolean = false,
    val selectedOption: Int = R.string.feature_appointment_booking_myself, // Use string resources here
    // Add other state properties as needed
) : BaseState