

package com.lwbd.lwbdpoc.core.network

import NetworkUrgentDoctor
import com.lwbd.lwbdpoc.core.network.model.NetworkSpecialist


/**
 * Interface representing network calls to the LWBD backend
 */
interface LwbdNetworkDataSource {
//    suspend fun getUrgentDoctors(): List<NetworkUrgentDoctor>
    suspend fun getUrgentDoctor(id: String? = null): NetworkUrgentDoctor
    suspend fun getSpecialists(): List<NetworkSpecialist>
}
