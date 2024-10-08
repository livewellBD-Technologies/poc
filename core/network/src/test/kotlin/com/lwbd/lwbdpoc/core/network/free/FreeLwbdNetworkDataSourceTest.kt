

package com.lwbd.lwbdpoc.core.network.free

import JvmUnitTestFreeAssetManager
import NetworkSpecialization
import NetworkUrgentDoctor
import com.lwbd.lwbdpoc.core.network.model.NetworkSpecialist
import com.lwbd.lwbdpoc.core.network.model.NetworkSpecializationExtended
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class FreeLwbdNetworkDataSourceTest {

    private lateinit var subject: FreeLwbdNetworkDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        subject = FreeLwbdNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestFreeAssetManager,
        )
    }

    @Suppress("ktlint:standard:max-line-length")
    @Test
    fun testDeserializationSpecialists() = runTest(testDispatcher) {
        assertEquals(
            NetworkSpecialist(
                id="64245104dc683ab14b98de81",
            specializations= NetworkSpecializationExtended(
            id="63009a304cfa23ff4a63f30f",
            name="Gynecology",
            imageUrl="https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/specialties/Gynecology.png",
            bannerUrlRect="https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/1688578974283-669978439.png",
            bannerUrlSqr="https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/1688578974258-229825068.png",
            createdAt="2022-08-20T08:24:16.736Z",
            updatedAt="2023-10-01T13:12:46.934Z",
            trainings=listOf(
            "PGT",
            "FCPS Part-1",
            "FCPS Part-2"
            ),
            services=listOf(
            "Sexual and reproductive health counseling",
            "Cesarean delivery",
            "Hysterectomy",
            "Oophorectomy",
            "Myomectomy",
            "Tubal ligation",
            "Hysteroscopy",
            "Colposcopy",
            "Pelvic organ prolapse repair",
            "Pelvic reconstructive surgery",
            "Endometrial ablation",
            "Pelvic laparotomy",
            "Gynecologic oncology surgeries",
            "Urinary incontinence surgery"
            ),
            isActive=true
            ),
        fee="200",
        nextAvailableSlot="AVAILABLE NOW"
            ),
            subject.getSpecialists().first(),
        )
    }

    @Suppress("ktlint:standard:max-line-length")
    @Test
    fun testDeserializationOfUrgentDoctor() = runTest(testDispatcher) {
        assertEquals(
            NetworkUrgentDoctor(
                id = "6424513cdc683ab14b98de82",
                specializations = NetworkSpecialization(
                    id = "63009a304cfa23ff4a63f30e",
                    name = "General Physician",
                    imageUrl = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/specialties/General+Physician.png",
                    bannerUrlRect = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/1688579077777-10848301.png",
                    bannerUrlSqr = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/1688579077772-547352447.png",
                    createdAt = "2022-08-20T08:24:16.665Z",
                    updatedAt = "2023-05-24T15:46:11.970Z",
                    trainings = listOf(
                        "FCGP"
                    ),
                    isActive = true,
                ),
                fee = "75"
            ),
            subject.getUrgentDoctor(),
        )
    }
}
