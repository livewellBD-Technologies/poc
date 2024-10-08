package com.lwbd.lwbdpoc.core.data.test.repository


import com.lwbd.lwbdpoc.core.data.model.asExternalModel
import com.lwbd.lwbdpoc.core.data.repository.DoctorRepository
import com.lwbd.lwbdpoc.core.model.data.Specialist
import com.lwbd.lwbdpoc.core.model.data.UrgentDoctor
import com.lwbd.lwbdpoc.core.network.Dispatcher
import com.lwbd.lwbdpoc.core.network.LwbdDispatchers.IO
import com.lwbd.lwbdpoc.core.network.free.FreeLwbdNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Fake implementation of the [DoctorRepository] that retrieves the doctor or doctors from a JSON String, and
 * uses a local DataStore instance to save and retrieve followed topic ids.
 *
 * This allows us to run the app with fake data, without needing an internet connection or working
 * backend.
 */
internal class FakeDoctorRepository @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val datasource: FreeLwbdNetworkDataSource,
) : DoctorRepository {

    override suspend fun getUrgentDoctor(id: String?): UrgentDoctor {
        return datasource.getUrgentDoctor().asExternalModel()
    }

    override suspend fun getSpecialists(): List<Specialist> {
        return datasource.getSpecialists().map { specialist -> specialist.asExternalModel() }
    }

}
