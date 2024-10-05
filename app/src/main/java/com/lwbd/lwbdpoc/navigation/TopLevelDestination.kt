package com.lwbd.lwbdpoc.navigation

import com.lwbd.lwbdpoc.core.designsystem.R
import com.lwbd.lwbdpoc.feature.finddoctor.R as bookmarksR
import com.lwbd.lwbdpoc.feature.home.R as homeR
import com.lwbd.lwbdpoc.feature.menu.R as menuR
import com.lwbd.lwbdpoc.feature.prescriptions.R as prescriptionsR

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */

enum class TopLevelDestination(
    val selectedIconResId: Int,
    val unselectedIconResId: Int,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIconResId = R.drawable.core_designsystem_home,
        unselectedIconResId = R.drawable.core_designsystem_home,
        iconTextId = homeR.string.feature_home_title,
        titleTextId = homeR.string.feature_home_empty,
    ),
    FIND_DOCTOR(
        selectedIconResId = R.drawable.core_designsystem_find_doctor,
        unselectedIconResId = R.drawable.core_designsystem_find_doctor,
        iconTextId = bookmarksR.string.feature_find_doctor_title,
        titleTextId = bookmarksR.string.feature_find_doctor_title,
    ),
    PRESCRIPTIONS(
        selectedIconResId = R.drawable.core_designsystem_prescription,
        unselectedIconResId = R.drawable.core_designsystem_prescription,
        iconTextId = prescriptionsR.string.feature_prescriptions_title,
        titleTextId = prescriptionsR.string.feature_prescriptions_title,
    ),
    MENU(
        selectedIconResId = R.drawable.core_designsystem_menu,
        unselectedIconResId = R.drawable.core_designsystem_menu,
        iconTextId = menuR.string.feature_menu_title,
        titleTextId = menuR.string.feature_menu_title,
    ),
}
