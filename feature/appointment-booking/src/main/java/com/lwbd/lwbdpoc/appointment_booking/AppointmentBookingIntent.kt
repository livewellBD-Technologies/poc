package com.lwbd.lwbdpoc.appointment_booking

import com.lwbd.lwbdpoc.core.base.BaseIntent

sealed interface AppointmentBookingIntent : BaseIntent {
    data object ShowBottomSheet : AppointmentBookingIntent
    data object HideBottomSheet : AppointmentBookingIntent
    data class SelectOption(val option: Int) : AppointmentBookingIntent
    data object GoBack : AppointmentBookingIntent
    data object GoNext : AppointmentBookingIntent
}