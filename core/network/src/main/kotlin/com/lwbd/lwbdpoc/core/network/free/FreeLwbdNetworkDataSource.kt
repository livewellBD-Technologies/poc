/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lwbd.lwbdpoc.core.network.free

import JvmUnitTestFreeAssetManager
import NetworkUrgentDoctor


import com.lwbd.lwbdpoc.core.network.Dispatcher
import com.lwbd.lwbdpoc.core.network.LwbdDispatchers.IO
import com.lwbd.lwbdpoc.core.network.LwbdNetworkDataSource
import com.lwbd.lwbdpoc.core.network.model.NetworkSpecialist



import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import okio.use
import java.io.InputStream
import javax.inject.Inject

/**
 * [LwbdNetworkDataSource] implementation that provides static news resources to aid development
 */
class FreeLwbdNetworkDataSource @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FreeAssetManager = JvmUnitTestFreeAssetManager,
) : LwbdNetworkDataSource {



    companion object {
        private const val URGENT_DOCTOR_ASSET = "urgent_doctor.json"
        private const val SPECIALISTS_ASSET = "specialists.json"
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getUrgentDoctor(id: String?): NetworkUrgentDoctor {
        // Switch the coroutine context to the IO dispatcher
        return withContext(ioDispatcher) {
            // Open the asset and use it within the scope
            assets.open(URGENT_DOCTOR_ASSET).use { inputStream: InputStream ->
                // Deserialize JSON from the InputStream
                Json.decodeFromStream(inputStream)
            }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getSpecialists(): List<NetworkSpecialist> {
        return withContext(ioDispatcher) {
            // Open the asset and use it within the scope
            assets.open(SPECIALISTS_ASSET).use { inputStream: InputStream ->
                // Deserialize JSON from the InputStream
                Json.decodeFromStream(inputStream)
            }
        }
    }
}


