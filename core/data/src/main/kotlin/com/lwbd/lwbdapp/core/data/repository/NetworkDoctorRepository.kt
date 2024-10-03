package com.lwbd.lwbdpoc.core.data.repository


import com.lwbd.lwbdpoc.core.data.model.asExternalModel
import com.lwbd.lwbdpoc.core.model.data.Specialist
import com.lwbd.lwbdpoc.core.model.data.UrgentDoctor
import com.lwbd.lwbdpoc.core.network.LwbdNetworkDataSource
import com.lwbd.lwbdpoc.core.network.model.NetworkSpecialist

import javax.inject.Inject

/**
 * Disk storage backed implementation of the [NetworkDoctorRepository].
 */
internal class NetworkDoctorRepository @Inject constructor(

    private val network: LwbdNetworkDataSource,
) : DoctorRepository {

    override suspend fun getUrgentDoctor(id: String?): UrgentDoctor {
        val urgentDoctor = network.getUrgentDoctor(id = id)
        return urgentDoctor.asExternalModel()
    }

    override suspend fun getSpecialists(): List<Specialist> {
        val specialists = network.getSpecialists()
        return specialists.map(NetworkSpecialist::asExternalModel)
    }

}

