package com.lwbd.lwbdpoc.appointment_booking

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class AppointmentBookingViewModelTest {


    private lateinit var appointmentBookingViewModel: AppointmentBookingViewModel

    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setup() {
        // Set the test dispatcher
        Dispatchers.setMain(testDispatcher)

        // Mock the repository


        // Instantiate the ViewModel with the mocked repository
        appointmentBookingViewModel = AppointmentBookingViewModel()
    }

    @Test
    fun test_initial_selection_is_my_self() = runTest {

//

        // Act - process the LoadUrgentDoctor intent
        appointmentBookingViewModel.state.test {

            appointmentBookingViewModel.processIntent(
                AppointmentBookingIntent.SelectOption(
                    appointmentBookingViewModel.mySelf
                )
            )
//            println(awaitItem())
            assertEquals(
                AppointmentBookingState(
                    selectedOption = appointmentBookingViewModel.mySelf,
                    showSheet = false
                ),
                awaitItem()
            )

            appointmentBookingViewModel.processIntent(
                AppointmentBookingIntent.SelectOption(
                    appointmentBookingViewModel.someoneElse
                )
            )
            assertEquals(
                AppointmentBookingState(
                    selectedOption = appointmentBookingViewModel.someoneElse,
                    showSheet = false
                ),
                awaitItem()
            )



        }
    }


    // Add more tests for other UI elements, interactions, etc.
}