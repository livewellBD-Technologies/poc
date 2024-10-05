

package com.lwbd.lwbdpoc.core.testing.di

import com.lwbd.lwbdpoc.core.network.Dispatcher
import com.lwbd.lwbdpoc.core.network.LwbdDispatchers.Default
import com.lwbd.lwbdpoc.core.network.LwbdDispatchers.IO
import com.lwbd.lwbdpoc.core.network.di.DispatchersModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatchersModule::class],
)
internal object TestDispatchersModule {
    @Provides
    @Dispatcher(IO)
    fun providesIODispatcher(testDispatcher: TestDispatcher): CoroutineDispatcher = testDispatcher

    @Provides
    @Dispatcher(Default)
    fun providesDefaultDispatcher(
        testDispatcher: TestDispatcher,
    ): CoroutineDispatcher = testDispatcher
}
