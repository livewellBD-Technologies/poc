package com.lwbd.lwbdpoc.feature.home

import SpecialistUiState
import com.lwbd.lwbdpoc.core.base.BaseState

//import com.lwbd.lwbdpoc.core.model.data.UserNewsResource
import com.lwbd.lwbdpoc.core.ui.UrgentDoctorUiState


data class HomeState(
    val shouldShowOnboarding: Boolean = false,
    val urgentDoctorUiState: UrgentDoctorUiState = UrgentDoctorUiState.Loading,
    val specialistUiState: SpecialistUiState = SpecialistUiState.Loading,
//    val deepLinkedNewsResource: UserNewsResource? = null
):BaseState





