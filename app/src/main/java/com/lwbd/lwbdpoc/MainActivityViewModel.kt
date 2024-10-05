package com.lwbd.lwbdpoc

import androidx.lifecycle.ViewModel
import com.lwbd.lwbdpoc.core.model.data.UrgentDoctor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
//    doctorRepository: DoctorRepository,
) : ViewModel()

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val urgentDoctor: UrgentDoctor) : MainActivityUiState
}
