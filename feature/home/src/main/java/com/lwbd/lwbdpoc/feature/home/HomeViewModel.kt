package com.lwbd.lwbdpoc.feature.home

import SpecialistUiState
import androidx.lifecycle.viewModelScope
import com.lwbd.lwbdpoc.core.base.BaseViewModel
import com.lwbd.lwbdpoc.core.data.repository.DoctorRepository
import com.lwbd.lwbdpoc.core.ui.UrgentDoctorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val doctorRepository: DoctorRepository,
) : BaseViewModel<HomeIntent, HomeState>() {

    init {
        processIntent(HomeIntent.LoadUrgentDoctor())
        processIntent(HomeIntent.LoadSpecialists)
    }

    override fun processIntent(intent: HomeIntent) {
        handleIntent(intent);
    }


    private fun loadUrgentDoctor(id: String? = null) {
        viewModelScope.launch {
            setState(state.value.copy(urgentDoctorUiState = UrgentDoctorUiState.Loading))
            val urgentDoctor = doctorRepository.getUrgentDoctor(id = id)
            setState(state.value.copy(urgentDoctorUiState = UrgentDoctorUiState.Success(urgentDoctor)))

        }

    }

    private fun loadSpecialists() {
        viewModelScope.launch {
            setState(state.value.copy(specialistUiState = SpecialistUiState.Loading))
            val specialists =  doctorRepository.getSpecialists()
//        Log.d("HomeViewModel", "loadSpecialists: $specialists")
            setState(state.value.copy(specialistUiState = SpecialistUiState.Success(specialists)))
        }
    }


    override fun initialState(): HomeState {
        return HomeState(
            specialistUiState = SpecialistUiState.Loading,
            urgentDoctorUiState = UrgentDoctorUiState.Loading,
        )
    }

    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadUrgentDoctor -> loadUrgentDoctor(intent.id)
            is HomeIntent.LoadSpecialists -> loadSpecialists()

        }
    }
}

