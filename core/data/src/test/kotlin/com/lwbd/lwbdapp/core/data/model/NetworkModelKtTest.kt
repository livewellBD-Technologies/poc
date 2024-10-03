package com.lwbd.lwbdpoc.core.data.model

import NetworkSpecialization
import NetworkUrgentDoctor
import com.lwbd.lwbdpoc.core.network.model.NetworkSpecialist
import com.lwbd.lwbdpoc.core.network.model.NetworkSpecializationExtended
import org.junit.Test
import kotlin.test.assertEquals

class NetworkModelKtTest {

    @Test
    fun network_urgent_doctor_can_be_mapped_to_network_doctor_model() {
        val networkModel = NetworkUrgentDoctor(
            id = "1",
            specializations = NetworkSpecialization(
                bannerUrlRect = "Banner URL Rect",
                bannerUrlSqr = "Banner URL Sqr",
                imageUrl = "Image URL",
                name = "Doctor",
                createdAt = "2023-05-24T15:46:11.970Z",
                updatedAt = "2023-05-24T15:46:11.970Z",
                trainings = listOf("Training 1", "Training 2"),
                id = "2",
                isActive = true
            ),
            fee = "75"
        )
        val model = networkModel.asExternalModel()

        assertEquals("1", model.id)
        assertEquals("Doctor", model.specializations?.name)
        assertEquals("Banner URL Sqr", model.specializations?.bannerUrlSqr)
        assertEquals("Training 1", model.specializations?.trainings?.first())
        assertEquals("75", model.fee)
        assertEquals("Image URL", model.specializations?.imageUrl)
    }

    @Test
    fun network_specialist_can_be_mapped_to_specialist_model() {
        val networkModel =
            NetworkSpecialist(
                id = "64245104dc683ab14b98de81",
                specializations = NetworkSpecializationExtended(
                    id = "63009a304cfa23ff4a63f30f",
                    name = "Gynecology",
                    imageUrl = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/specialties/Gynecology.png",
                    bannerUrlRect = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/1688578974283-669978439.png",
                    bannerUrlSqr = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/1688578974258-229825068.png",
                    createdAt = "2022-08-20T08:24:16.736Z",
                    updatedAt = "2023-10-01T13:12:46.934Z",
                    trainings = listOf(
                        "PGT",
                        "FCPS Part-1",
                        "FCPS Part-2"
                    ),
                    services = listOf(
                        "Hysterectomy",
                        "Sexual and reproductive health counseling",
                        "Cesarean delivery",
                        "Oophorectomy",
                    ),
                    isActive = true
                ),
                fee = "200",
                nextAvailableSlot = "AVAILABLE NOW"
            )
        val model = networkModel.asExternalModel()

        assertEquals("64245104dc683ab14b98de81", model.id)
        assertEquals("Gynecology", model.specializations.name)
        assertEquals("200", model.fee)
        assertEquals(
            "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/specialties/Gynecology.png",
            model.specializations.imageUrl
        )
        assertEquals("Hysterectomy", model.specializations.services?.first())


        val expandedNetworkModel =
            NetworkSpecializationExtended(
                id = "63009a304cfa23ff4a63f30f",
                name = "Gynecology",
                imageUrl = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/specialties/Gynecology.png",
                bannerUrlRect = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/1688578974283-669978439.png",
                bannerUrlSqr = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/1688578974258-229825068.png",
                createdAt = "2022-08-20T08:24:16.736Z",
                updatedAt = "2023-10-01T13:12:46.934Z",
                trainings = listOf(
                    "PGT",
                    "FCPS Part-1",
                    "FCPS Part-2"
                ),
                services = listOf(
                    "Hysterectomy",
                    "Sexual and reproductive health counseling",
                    "Cesarean delivery",
                    "Oophorectomy",
                ),
                isActive = true
            )

        val modelFromExpanded = expandedNetworkModel.asExternalModel()

        assertEquals("63009a304cfa23ff4a63f30f", modelFromExpanded.id)
        assertEquals("Gynecology", modelFromExpanded.name)
        assertEquals(true, modelFromExpanded.isActive)
        assertEquals("Oophorectomy", modelFromExpanded.services?.last())
        assertEquals("PGT", modelFromExpanded.trainings.first())
    }
}
