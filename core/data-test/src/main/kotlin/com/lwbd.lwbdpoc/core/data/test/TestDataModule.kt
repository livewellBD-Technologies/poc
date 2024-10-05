package com.lwbd.lwbdpoc.core.data.test

import com.lwbd.lwbdpoc.core.data.di.DataModule
import com.lwbd.lwbdpoc.core.data.repository.DoctorRepository
import com.lwbd.lwbdpoc.core.data.test.repository.FakeDoctorRepository

import com.lwbd.lwbdpoc.core.data.util.NetworkMonitor
import com.lwbd.lwbdpoc.core.data.util.TimeZoneMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class],
)
internal interface TestDataModule {
    @Binds
    fun bindsDoctorRepository(
        fakeDoctorRepository: FakeDoctorRepository,
    ): DoctorRepository


    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: AlwaysOnlineNetworkMonitor,
    ): NetworkMonitor

    @Binds
    fun binds(impl: DefaultZoneIdTimeZoneMonitor): TimeZoneMonitor
}
