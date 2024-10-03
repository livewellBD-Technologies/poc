

package com.lwbd.lwbdpoc.core.data.di


import com.lwbd.lwbdpoc.core.data.repository.NetworkDoctorRepository
import com.lwbd.lwbdpoc.core.data.repository.DoctorRepository

import com.lwbd.lwbdpoc.core.data.util.ConnectivityManagerNetworkMonitor
import com.lwbd.lwbdpoc.core.data.util.NetworkMonitor
import com.lwbd.lwbdpoc.core.data.util.TimeZoneBroadcastMonitor
import com.lwbd.lwbdpoc.core.data.util.TimeZoneMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

//    @Binds
//    internal abstract fun bindsDoctorRepository(
//        urgentDoctorRepository: NetworkDoctorRepository,
//    ): DoctorRepository


    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    @Binds
    internal abstract fun binds(impl: TimeZoneBroadcastMonitor): TimeZoneMonitor
}
