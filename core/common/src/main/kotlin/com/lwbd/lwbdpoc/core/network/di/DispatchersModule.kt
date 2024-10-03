package com.lwbd.lwbdpoc.core.network.di

import com.lwbd.lwbdpoc.core.network.Dispatcher
import com.lwbd.lwbdpoc.core.network.LwbdDispatchers.Default
import com.lwbd.lwbdpoc.core.network.LwbdDispatchers.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
