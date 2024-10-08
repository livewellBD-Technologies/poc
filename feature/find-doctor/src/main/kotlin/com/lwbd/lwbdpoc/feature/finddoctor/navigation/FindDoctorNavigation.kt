

package com.lwbd.lwbdpoc.feature.finddoctor.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lwbd.lwbdpoc.feature.finddoctor.FindDoctorRoute
import kotlin.reflect.KFunction1

const val FIND_DOCTOR_ROUTE = "find_doctor_route"

fun NavController.navigateToFindDoctor(navOptions: NavOptions) = navigate(FIND_DOCTOR_ROUTE, navOptions)

fun NavGraphBuilder.findDoctorScreen(
    onNavigationClick: KFunction1<NavOptions?, Unit>,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = FIND_DOCTOR_ROUTE) {
        FindDoctorRoute()
    }
}
