package com.lwbd.lwbdpoc.appointment_booking

import com.lwbd.lwbdpoc.apointment_booking.R
import com.lwbd.lwbdpoc.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AppointmentBookingViewModel @Inject constructor() :
    BaseViewModel<AppointmentBookingIntent, AppointmentBookingState>() {
    //    private val _state = mutableStateOf(AppointmentBookingState())
//    val state: State<AppointmentBookingState> = _state.asState()
    var mySelf: Int = R.string.feature_appointment_booking_myself
    var someoneElse: Int = R.string.feature_appointment_booking_someone_else

    init {
        processIntent(AppointmentBookingIntent.HideBottomSheet)

        processIntent(AppointmentBookingIntent.SelectOption(mySelf))
    }





    override fun processIntent(intent: AppointmentBookingIntent) {
        handleIntent(intent)
    }


    override fun initialState(): AppointmentBookingState {
        return AppointmentBookingState(
            showSheet = false,
            selectedOption = mySelf
        )
    }

    override fun handleIntent(intent: AppointmentBookingIntent) {

        when (intent) {
            is AppointmentBookingIntent.ShowBottomSheet -> {
                setState(state.value.copy(showSheet = true))
            }
            is AppointmentBookingIntent.HideBottomSheet -> setState(state.value.copy(showSheet = false))
            is AppointmentBookingIntent.SelectOption -> setState(state.value.copy(selectedOption = intent.option))
            // Handle other intents (navigation, etc.)
            AppointmentBookingIntent.GoBack -> TODO()
            AppointmentBookingIntent.GoNext -> TODO()
        }
    }
}






