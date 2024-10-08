package com.lwbd.lwbdpoc.feature.home

import com.lwbd.lwbdpoc.core.base.BaseIntent

sealed class HomeIntent : BaseIntent {
    data class LoadUrgentDoctor(val id: String?=null) : HomeIntent()
    data object LoadSpecialists : HomeIntent()
}