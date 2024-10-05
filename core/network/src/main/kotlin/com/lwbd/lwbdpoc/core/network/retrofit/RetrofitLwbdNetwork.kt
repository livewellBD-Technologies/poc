package com.lwbd.lwbdpoc.core.network.retrofit

import NetworkUrgentDoctor
import android.util.Log
import androidx.tracing.trace
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lwbd.lwbdpoc.core.network.BuildConfig
import com.lwbd.lwbdpoc.core.network.LwbdNetworkDataSource
import com.lwbd.lwbdpoc.core.network.model.NetworkSpecialist
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Retrofit API declaration for LWBD Network API
 */
private interface RetrofitLwbdNetworkApi {
//    @GET(value = "doctors/urgent/specialization/")
//    suspend fun getUrgentDoctors(
//    ): NetworkResponse<List<NetworkUrgentDoctor>>

    @GET(value = "doctors/urgent/specialization/6424513cdc683ab14b98de82")
    suspend fun getUrgentDoctor(
//        @Path("id") id: String?,
    ): NetworkResponse<NetworkUrgentDoctor>

    @GET(value = "doctors/urgent/specialties")
    suspend fun getSpecialities(): NetworkResponse<List<NetworkSpecialist>>

}

private const val LWBD_BASE_URL = BuildConfig.BACKEND_URL

/**
 * Wrapper for data provided from the [LWBD_BASE_URL]
 */
@Serializable
private data class NetworkResponse<T>(
    val data: T,
    val message: String?=null
)

/**
 * [Retrofit] backed [LwbdNetworkDataSource]
 */
@Singleton
internal class RetrofitLwbdNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : LwbdNetworkDataSource {

    private val networkApi = trace("RetrofitLwbdNetwork") {
        Retrofit.Builder()
            .baseUrl(LWBD_BASE_URL)
            // We use callFactory lambda here with dagger.Lazy<Call.Factory>
            // to prevent initializing OkHttp on the main thread.
            .callFactory {
                Log.d("RetrofitLwbdNetwork", "callFactory: $it")
                okhttpCallFactory.get().newCall(it)
            }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(RetrofitLwbdNetworkApi::class.java)
    }

//    override suspend fun getUrgentDoctors(): List<NetworkUrgentDoctor> {

//     val response=  networkApi.getUrgentDoctors().data
//        Log.d("NetworkResponse", "Urgent Doctors: $response")
//
//        return response;
//    }

    override suspend fun getUrgentDoctor(id: String?): NetworkUrgentDoctor =
        networkApi.getUrgentDoctor().data

    override suspend fun getSpecialists(): List<NetworkSpecialist> =
        networkApi.getSpecialities().data

}
