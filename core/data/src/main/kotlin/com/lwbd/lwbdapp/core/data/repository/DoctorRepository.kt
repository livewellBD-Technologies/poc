

package com.lwbd.lwbdpoc.core.data.repository


import com.lwbd.lwbdpoc.core.model.data.Specialist
import com.lwbd.lwbdpoc.core.model.data.UrgentDoctor

interface DoctorRepository  {
    /**
     * Gets the available topics as a stream
     */
//    fun getUrgentDoctors(): Flow<List<UrgentDoctor>>

    /**
     * Gets data for a specific topic
     */
   suspend fun getUrgentDoctor(id: String?=null): UrgentDoctor
   suspend fun getSpecialists(): List<Specialist>
}
