

package com.lwbd.lwbdpoc.core.network.di

import com.lwbd.lwbdpoc.core.network.LwbdNetworkDataSource
//import com.lwbd.lwbdpoc.core.network.demo.DemoLwbdNetworkDataSource
import com.lwbd.lwbdpoc.core.network.retrofit.RetrofitLwbdNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DemoFlavoredNetworkModule {

    @Binds
    fun binds(impl: RetrofitLwbdNetwork): LwbdNetworkDataSource
}
