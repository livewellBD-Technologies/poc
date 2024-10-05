/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lwbd.lwbdpoc.ui

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import com.lwbd.lwbdpoc.MainActivity
import com.lwbd.lwbdpoc.R
import com.lwbd.lwbdpoc.core.data.repository.DoctorRepository
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import javax.inject.Inject
import com.lwbd.lwbdpoc.feature.finddoctor.R as FindDoctorR
import com.lwbd.lwbdpoc.feature.home.R as FeatureHomeR

/**
 * Tests all the navigation flows that are handled by the navigation library.
 */
@HiltAndroidTest
class NavigationTest {

    /**
     * Manages the components' state and is used to perform injection on your test
     */
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    /**
     * Create a temporary folder used to create a Data Store file. This guarantees that
     * the file is removed in between each test, preventing a crash.
     */
    @BindValue
    @get:Rule(order = 1)
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()



    /**
     * Use the primary activity to initialize the app normally.
     */
    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var doctorRepository: DoctorRepository

    // The strings used for matching in these tests
    private val navigateUp by composeTestRule.stringResource(FeatureHomeR.string.feature_home_navigate_up)
    private val home by composeTestRule.stringResource(FeatureHomeR.string.feature_home_title)

    private val callNow by composeTestRule.stringResource(com.lwbd.lwbdpoc.core.ui.R.string.core_ui_card_call_now)
    private val appName by composeTestRule.stringResource(R.string.app_name)
    private val findDoctor by composeTestRule.stringResource(FindDoctorR.string.feature_find_doctor_title)


    @Before
    fun setup() = hiltRule.inject()

    @Test
    fun firstScreen_isHome() {
        composeTestRule.apply {
            // VERIFY for you is selected
            onNodeWithText(home).assertIsSelected()
        }
    }

    // TODO: implement tests related to navigation & resetting of destinations (b/213307564)
    // Restoring content should be tested with another tab than the For You one, as that will
    // still succeed even when restoring state is turned off.
    /**
     * When navigating between the different top level destinations, we should restore the state
     * of previously visited destinations.
     */
//    @Test
//    fun navigationBar_navigateToPreviouslySelectedTab_restoresContent() {
//        composeTestRule.apply {
//            // GIVEN the user follows a topic
//            onNodeWithText(callNow).performClick()
//            // WHEN the user navigates to the Interests destination
////            onNodeWithText(interests).performClick()
//            // AND the user navigates to the For You destination
//            onNodeWithText(home).performClick()
//            // THEN the state of the For You destination is restored
//            onNodeWithContentDescription(callNow).assertIsOn()
//        }
//    }

    /*
     * Top level destinations should never show an up affordance.
     */
    @Test
    fun topLevelDestinations_doNotShowUpArrow() {
        composeTestRule.apply {
            // GIVEN the user is on any of the top level destinations, THEN the Up arrow is not shown.
            onNodeWithContentDescription(navigateUp).assertDoesNotExist()

            onNodeWithText(findDoctor).performClick()
            onNodeWithContentDescription(navigateUp).assertDoesNotExist()


            onNodeWithContentDescription(navigateUp).assertDoesNotExist()
        }
    }

    @Test
    fun topLevelDestinations_showTopBarWithTitle() {
        composeTestRule.apply {
            // Verify that the top bar contains the app name on the first screen.
            onNodeWithTag("LwbdTopAppBar",true).assertExists()

            // Go to the saved tab, verify that the top bar contains "saved". This means
            // we'll have 2 elements with the text "saved" on screen. One in the top bar, and
            // one in the bottom navigation.
            onNodeWithText(findDoctor).performClick()
            onAllNodesWithText(findDoctor).assertCountEquals(2)

            // As above but for the interests tab.
//            onNodeWithText(interests).performClick()
//            onAllNodesWithText(interests).assertCountEquals(2)
        }
    }




    /*
     * There should always be at most one instance of a top-level destination at the same time.
     */
    @Test(expected = NoActivityResumedException::class)
    fun homeDestination_back_quitsApp() {
        composeTestRule.apply {
            // GIVEN the user navigates to the Interests destination
//            onNodeWithText(interests).performClick()
            // and then navigates to the For you destination
            onNodeWithText(home).performClick()
            // WHEN the user uses the system button/gesture to go back
            Espresso.pressBack()
            // THEN the app quits
        }
    }

    /*
     * When pressing back from any top level destination except "For you", the app navigates back
     * to the "For you" destination, no matter which destinations you visited in between.
     */
    @Test
    fun navigationBar_backFromAnyDestination_returnsToHome() {
        composeTestRule.apply {
            // GIVEN the user navigated to the patient journey destination
            onNodeWithText(callNow).performClick()
            // TODO: Add another destination here to increase test coverage, see b/226357686.
            // WHEN the user uses the system button/gesture to go back,
            Espresso.pressBack()
            // THEN the app shows the For You destination
            onNodeWithText(home).assertExists()
        }
    }


}
