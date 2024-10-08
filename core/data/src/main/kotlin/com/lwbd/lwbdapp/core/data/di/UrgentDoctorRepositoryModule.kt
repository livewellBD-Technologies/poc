

package com.lwbd.lwbdpoc.core.data.di

import com.lwbd.lwbdpoc.core.data.repository.NetworkDoctorRepository
import com.lwbd.lwbdpoc.core.data.repository.DoctorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UrgentDoctorRepositoryModule {
    @Binds
    fun bindsDoctorRepository(
        userDataRepository: NetworkDoctorRepository,
    ): DoctorRepository
}
