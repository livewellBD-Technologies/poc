package com.lwbd.lwbdpoc.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val lwbdDispatcher: LwbdDispatchers)

enum class LwbdDispatchers {
    Default,
    IO,
}
