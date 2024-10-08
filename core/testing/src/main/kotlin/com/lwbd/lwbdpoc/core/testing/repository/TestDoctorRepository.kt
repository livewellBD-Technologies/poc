package com.lwbd.lwbdpoc.core.testing.repository


import com.lwbd.lwbdpoc.core.data.repository.DoctorRepository
import com.lwbd.lwbdpoc.core.model.data.Specialist
import com.lwbd.lwbdpoc.core.model.data.Specialization
import com.lwbd.lwbdpoc.core.model.data.SpecializationExtended
import com.lwbd.lwbdpoc.core.model.data.UrgentDoctor

class TestDoctorRepository : DoctorRepository {

    /**
     * The backing hot flow for the list of topics ids for testing.
     */
    private val specialistResource: List<Specialist> = mutableListOf(
        Specialist(
            id = "64245104dc683ab14b98de81",
            specializations = SpecializationExtended(
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
                isActive = true
            ),
            fee = "200",
            nextAvailableSlot = "AVAILABLE NOW"
        ),
        Specialist(
            id = "64245104dc683ab14b98de82",
            specializations = SpecializationExtended(
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
                isActive = true
            ),
            fee = "100",
            nextAvailableSlot = "AVAILABLE NOW"
        )
    )
    private val urgentDoctorResource: UrgentDoctor = UrgentDoctor(
        id = "1",
        specializations = Specialization(
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

    override suspend fun getSpecialists(): List<Specialist> =

        specialistResource.map { specialist ->
            specialist
        }


    override suspend fun getUrgentDoctor(id: String?): UrgentDoctor {
        return urgentDoctorResource
    }
}
