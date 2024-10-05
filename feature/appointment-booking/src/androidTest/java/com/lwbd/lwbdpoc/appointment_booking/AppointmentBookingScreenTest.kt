package com.lwbd.lwbdpoc.appointment_booking

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.lwbd.lwbdpoc.core.designsystem.theme.LwbdTheme
import org.junit.Rule
import org.junit.Test


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class AppointmentBookingScreenAndroidTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>() // Replace YourActivity


    @Test
    fun appointmentBookingScreen_clickNextButton_showsBottomSheet() {
        composeTestRule.setContent {
            LwbdTheme {
                Box {
                    AppointmentBookingScreen(
                        navController = rememberNavController(),
                        state = AppointmentBookingState(showSheet = false),
                        onIntent = {},
                        modifier = Modifier
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Completed Journey")
            .assertExists() // Assuming this text is in the bottom sheet
        composeTestRule.onNodeWithTag("CloseButton").performClick()
        composeTestRule.onNodeWithText("Completed Journey").assertDoesNotExist()

    }

    // Add more tests for navigation, user interactions, etc.
}